package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dlss.entity.Annotation;
import project.dlss.entity.Review;
import project.dlss.entity.User;
import project.dlss.exception.BadRequestException;
import project.dlss.exception.ResourceNotFoundException;
import project.dlss.repository.AnnotationRepository;
import project.dlss.repository.ReviewRepository;
import project.dlss.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AnnotationRepository annotationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Review reviewAnnotation(Long annotationId,
                                   Long reviewerId,
                                   String status,
                                   String comment) {

        Annotation annotation = annotationRepository.findById(annotationId)
                .orElseThrow(() -> new ResourceNotFoundException("Annotation not found"));

        User reviewer = userRepository.findById(reviewerId)
                .orElseThrow(() -> new ResourceNotFoundException("Reviewer not found"));

        Review review = new Review();
        review.setAnnotation(annotation);
        review.setReviewer(reviewer);

        Review.ReviewStatus reviewStatus;
        try {
            reviewStatus = Review.ReviewStatus.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new BadRequestException("Invalid review status: " + status + ", expected: APPROVED or REJECTED");
        }

        review.setStatus(reviewStatus);
        review.setComment(comment);
        review.setReviewedAt(LocalDateTime.now());

        reviewRepository.save(review);

        if (reviewStatus == Review.ReviewStatus.APPROVED) {
            annotation.setStatus("APPROVED");
        } else {
            annotation.setStatus("REJECTED");
        }

        annotationRepository.save(annotation);

        return review;
    }
}