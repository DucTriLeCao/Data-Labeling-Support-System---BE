package project.dlss.service;

import project.dlss.entity.Review;

public interface ReviewService {

    Review reviewAnnotation(Long annotationId,
                            Long reviewerId,
                            String status,
                            String comment);
}