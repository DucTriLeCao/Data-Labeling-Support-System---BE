package project.dlss.service;

import project.dlss.dto.AnnotationResponse;
import project.dlss.entity.Annotation;

public interface AnnotationService {

    Annotation createAnnotation(Long dataItemId, Long userId, String labelValue);

    Annotation updateAnnotation(Long annotationId, String labelValue);

    Annotation submitAnnotation(Long annotationId);

    AnnotationResponse getAnnotation(Long id);
}
