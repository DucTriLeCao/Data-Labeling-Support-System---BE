package project.dlss.service;

import project.dlss.dto.AnnotationRequestDTO;
import project.dlss.entity.Annotation;

import java.util.List;

public interface AnnotationService {

    Annotation createAnnotation(AnnotationRequestDTO dto);

    List<Annotation> getAnnotationsByDataItem(Long dataItemId);

}