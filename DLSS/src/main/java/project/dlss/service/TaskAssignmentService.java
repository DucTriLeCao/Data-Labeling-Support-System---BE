package project.dlss.service;

public interface TaskAssignmentService {

    void assignDataItem(Long dataItemId, Long userId);

    void updateAssignmentStatus(Long assignmentId, String status);
}