package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.ReviewRequestDTO;
import project.dlss.entity.Annotation;
import project.dlss.entity.Review;
import project.dlss.entity.User;
import project.dlss.repository.AnnotationRepository;
import project.dlss.repository.ReviewRepository;
import project.dlss.repository.UserRepository;
import project.dlss.service.ReviewService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AnnotationRepository annotationRepository;
    private final UserRepository userRepository;

    @Override
    public Review reviewAnnotation(ReviewRequestDTO dto) {

        Annotation annotation = annotationRepository.findById(dto.getAnnotationId())
                .orElseThrow(() -> new RuntimeException("Annotation not found"));

        User reviewer = userRepository.findById(dto.getReviewerId())
                .orElseThrow(() -> new RuntimeException("Reviewer not found"));

        Review review = new Review();

        review.setAnnotation(annotation);
        review.setReviewer(reviewer);
        review.setComment(dto.getComment());
        review.setReviewedAt(LocalDateTime.now());

        if (dto.getStatus().equalsIgnoreCase("approved")) {
            review.setStatus(Review.ReviewStatus.APPROVED);
            annotation.setStatus("approved");
        } else {
            review.setStatus(Review.ReviewStatus.REJECTED);
            annotation.setStatus("rejected");
        }

        annotationRepository.save(annotation);

        return reviewRepository.save(review);
    }
}