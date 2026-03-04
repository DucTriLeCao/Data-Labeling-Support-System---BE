package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dlss.dto.TaskResponse;
import project.dlss.entity.DataItem;
import project.dlss.entity.DataItemAssignment;
import project.dlss.entity.User;
import project.dlss.repository.DataItemAssignmentRepository;
import project.dlss.repository.DataItemRepository;
import project.dlss.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskAssignmentServiceImpl implements TaskAssignmentService {

    private final DataItemRepository dataItemRepository;
    private final DataItemAssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void assignDataItem(Long dataItemId, Long userId) {

        DataItem item = dataItemRepository.findById(dataItemId)
                .orElseThrow(() -> new RuntimeException("Data item not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DataItemAssignment assignment = new DataItemAssignment();

        assignment.setDataItem(item);
        assignment.setUser(user);
        assignment.setStatus(DataItemAssignment.DataItemStatus.ASSIGNED.name());
        assignment.setAssignedAt(LocalDateTime.now());

        assignmentRepository.save(assignment);

        item.setStatus(DataItem.DataItemStatus.ASSIGNED);
        dataItemRepository.save(item);
    }

    @Override
    public void updateAssignmentStatus(Long assignmentId, String status) {

        DataItemAssignment assignment =
                assignmentRepository.findById(assignmentId)
                        .orElseThrow(() -> new RuntimeException("Assignment not found"));

        try {
            DataItemAssignment.DataItemStatus.valueOf(status);
            assignment.setStatus(status);
        } catch (IllegalArgumentException ex) {
            assignment.setStatus(status);
        }
        assignmentRepository.save(assignment);
    }

    @Override
    public List<TaskResponse> getMyTasks(Long userId) {
        List<DataItemAssignment> assignments =
                assignmentRepository.findByUser_IdOrderByAssignedAtDesc(userId);

        return assignments.stream()
                .map(a -> {
                    DataItem item = a.getDataItem();
                    return TaskResponse.builder()
                            .assignmentId(a.getId())
                            .dataItemId(item.getId())
                            .content(item.getContent())
                            .status(a.getStatus())
                            .assignedAt(a.getAssignedAt())
                            .datasetId(item.getDataset().getId())
                            .datasetName(item.getDataset().getName())
                            .projectId(item.getDataset().getProject().getId())
                            .projectName(item.getDataset().getProject().getName())
                            .build();
                })
                .collect(Collectors.toList());
    }
}