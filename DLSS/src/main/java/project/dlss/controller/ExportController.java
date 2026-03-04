package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dlss.service.ExportService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class ExportController {

    private final ExportService exportService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> export(
            @RequestParam(required = false) Long projectId,
            @RequestParam(required = false) Long datasetId,
            @RequestParam(defaultValue = "json") String format) {

        List<Map<String, Object>> data = exportService.exportApprovedData(projectId, datasetId, format);
        return ResponseEntity.ok(data);
    }
}
