package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.dlss.service.TaskAssignmentService;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskAssignmentService taskService;

    @PostMapping("/assign")
    public ResponseEntity<?> assignTask(
            @RequestParam Long dataItemId,
            @RequestParam Long userId) {

        taskService.assignDataItem(dataItemId, userId);

        return ResponseEntity.ok("Assigned successfully");
    }
}