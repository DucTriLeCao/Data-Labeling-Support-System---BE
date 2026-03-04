package project.dlss.service;

import project.dlss.dto.TaskResponse;

import java.util.List;

public interface TaskAssignmentService {

    void assignDataItem(Long dataItemId, Long userId);

    void updateAssignmentStatus(Long assignmentId, String status);

    List<TaskResponse> getMyTasks(Long userId);
}