package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.AnnotationRequestDTO;
import project.dlss.entity.Annotation;
import project.dlss.entity.DataItem;
import project.dlss.entity.User;
import project.dlss.repository.AnnotationRepository;
import project.dlss.repository.DataItemRepository;
import project.dlss.repository.UserRepository;
import project.dlss.service.AnnotationService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnotationServiceImpl implements AnnotationService {

    private final AnnotationRepository annotationRepository;
    private final DataItemRepository dataItemRepository;
    private final UserRepository userRepository;

    @Override
    public Annotation createAnnotation(AnnotationRequestDTO dto) {

        DataItem dataItem = dataItemRepository.findById(dto.getDataItemId())
                .orElseThrow(() -> new RuntimeException("DataItem not found"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Annotation annotation = new Annotation();

        annotation.setDataItem(dataItem);
        annotation.setUser(user);
        annotation.setLabelValue(dto.getLabelValue());
        annotation.setStatus("submitted");
        annotation.setCreatedAt(LocalDateTime.now());

        return annotationRepository.save(annotation);
    }

    @Override
    public List<Annotation> getAnnotationsByDataItem(Long dataItemId) {
        return annotationRepository.findByDataItemId(dataItemId);
    }
}