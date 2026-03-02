package project.dlss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.LabelCreateRequest;
import project.dlss.entity.Label;
import project.dlss.service.LabelService;

import java.util.List;

@RestController
@RequestMapping("/api/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping
    public ResponseEntity<?> createLabel(@Valid @RequestBody LabelCreateRequest request) {

        Label label = labelService.createLabel(
                request.getProjectId(),
                request.getName(),
                request.getParentId()
        );

        return ResponseEntity.ok(label);
    }

    @GetMapping("/{projectId}")
    public List<Label> getLabels(@PathVariable Long projectId) {
        return labelService.getLabelsByProject(projectId);
    }
}