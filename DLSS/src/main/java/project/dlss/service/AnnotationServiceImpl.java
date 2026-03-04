package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dlss.dto.AnnotationResponse;
import project.dlss.entity.Annotation;
import project.dlss.entity.DataItem;
import project.dlss.entity.DataItemAssignment;
import project.dlss.entity.User;
import project.dlss.exception.BadRequestException;
import project.dlss.exception.ResourceNotFoundException;
import project.dlss.repository.AnnotationRepository;
import project.dlss.repository.DataItemAssignmentRepository;
import project.dlss.repository.DataItemRepository;
import project.dlss.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnnotationServiceImpl implements AnnotationService {

    private final AnnotationRepository annotationRepository;
    private final DataItemRepository dataItemRepository;
    private final DataItemAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Annotation createAnnotation(Long dataItemId, Long userId, String labelValue) {
        validateLabelValue(labelValue);

        DataItem dataItem = dataItemRepository.findById(dataItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Data item not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!isUserAssignedToDataItem(dataItemId, userId)) {
            throw new BadRequestException("User is not assigned to this data item");
        }

        annotationRepository.findByDataItem_IdAndUser_Id(dataItemId, userId)
                .ifPresent(a -> {
                    throw new BadRequestException("Annotation already exists for this data item by this user");
                });

        Annotation annotation = new Annotation();
        annotation.setDataItem(dataItem);
        annotation.setUser(user);
        annotation.setLabelValue(labelValue);
        annotation.setStatus("draft");
        annotation.setVersion(1);
        annotation.setCreatedAt(LocalDateTime.now());

        annotation = annotationRepository.save(annotation);

        dataItem.setStatus(DataItem.DataItemStatus.ANNOTATED);
        dataItemRepository.save(dataItem);

        return annotation;
    }

    @Override
    @Transactional
    public Annotation updateAnnotation(Long annotationId, String labelValue) {
        validateLabelValue(labelValue);

        Annotation annotation = annotationRepository.findById(annotationId)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found"));

        if ("APPROVED".equals(annotation.getStatus()) || "REJECTED".equals(annotation.getStatus())) {
            throw new BadRequestException("Cannot edit annotation after review");
        }

        annotation.setLabelValue(labelValue);
        annotation.setVersion((annotation.getVersion() == null ? 1 : annotation.getVersion()) + 1);

        return annotationRepository.save(annotation);
    }

    @Override
    @Transactional
    public Annotation submitAnnotation(Long annotationId) {
        Annotation annotation = annotationRepository.findById(annotationId)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found"));

        if ("submitted".equals(annotation.getStatus())) {
            throw new BadRequestException("Annotation already submitted");
        }

        annotation.setStatus("submitted");
        return annotationRepository.save(annotation);
    }

    @Override
    public AnnotationResponse getAnnotation(Long id) {
        Annotation a = annotationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found"));

        return AnnotationResponse.builder()
                .id(a.getId())
                .dataItemId(a.getDataItem().getId())
                .userId(a.getUser().getId())
                .labelValue(a.getLabelValue())
                .status(a.getStatus())
                .version(a.getVersion() != null ? a.getVersion() : 1)
                .createdAt(a.getCreatedAt())
                .build();
    }

    private void validateLabelValue(String labelValue) {
        if (labelValue == null || labelValue.isBlank()) {
            throw new BadRequestException("labelValue is required");
        }
        if (labelValue.length() > 10000) {
            throw new BadRequestException("labelValue exceeds max length");
        }
    }

    private boolean isUserAssignedToDataItem(Long dataItemId, Long userId) {
        return assignmentRepository.findByDataItem_Id(dataItemId).stream()
                .anyMatch(a -> a.getUser().getId().equals(userId));
    }
}
