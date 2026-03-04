package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.TaskResponse;
import project.dlss.service.TaskAssignmentService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskAssignmentService taskService;

    @GetMapping("/my")
    public ResponseEntity<List<TaskResponse>> getMyTasks(@RequestParam Long userId) {
        List<TaskResponse> tasks = taskService.getMyTasks(userId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignTask(
            @RequestParam Long dataItemId,
            @RequestParam Long userId) {

        taskService.assignDataItem(dataItemId, userId);

        return ResponseEntity.ok("Assigned successfully");
    }
}