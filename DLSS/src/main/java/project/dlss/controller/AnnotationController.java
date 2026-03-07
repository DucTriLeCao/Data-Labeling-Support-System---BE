package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.AnnotationRequestDTO;
import project.dlss.entity.Annotation;
import project.dlss.service.AnnotationService;

import java.util.List;

@RestController
@RequestMapping("/api/annotations")
@RequiredArgsConstructor
public class AnnotationController {

    private final AnnotationService annotationService;

    @PostMapping
    public Annotation createAnnotation(@RequestBody AnnotationRequestDTO dto) {
        return annotationService.createAnnotation(dto);
    }

    @GetMapping("/data-item/{id}")
    public List<Annotation> getByDataItem(@PathVariable Long id) {
        return annotationService.getAnnotationsByDataItem(id);
    }
}