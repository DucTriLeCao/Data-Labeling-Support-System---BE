package project.dlss.service;

import project.dlss.dto.ReviewRequestDTO;
import project.dlss.entity.Review;

public interface ReviewService {

    Review reviewAnnotation(ReviewRequestDTO dto);

}