package project.dlss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.AnnotationRequest;
import project.dlss.dto.AnnotationResponse;
import project.dlss.entity.Annotation;
import project.dlss.service.AnnotationService;

@RestController
@RequestMapping("/api/annotations")
@RequiredArgsConstructor
public class AnnotationController {

    private final AnnotationService annotationService;

    @GetMapping("/{id}")
    public ResponseEntity<AnnotationResponse> get(@PathVariable Long id) {
        AnnotationResponse resp = annotationService.getAnnotation(id);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<AnnotationResponse> create(@Valid @RequestBody AnnotationRequest request) {
        Annotation ann = annotationService.createAnnotation(
                request.getDataItemId(),
                request.getUserId(),
                request.getLabelValue()
        );
        AnnotationResponse resp = annotationService.getAnnotation(ann.getId());
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}/submit")
    public ResponseEntity<AnnotationResponse> submit(@PathVariable Long id) {
        Annotation ann = annotationService.submitAnnotation(id);
        AnnotationResponse resp = annotationService.getAnnotation(ann.getId());
        return ResponseEntity.ok(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnotationResponse> update(
            @PathVariable Long id,
            @RequestBody java.util.Map<String, String> body) {
        String labelValue = body.get("labelValue");
        if (labelValue == null || labelValue.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Annotation ann = annotationService.updateAnnotation(id, labelValue);
        AnnotationResponse resp = annotationService.getAnnotation(ann.getId());
        return ResponseEntity.ok(resp);
    }
}
