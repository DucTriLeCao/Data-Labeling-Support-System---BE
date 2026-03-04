package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.dlss.entity.Annotation;
import project.dlss.entity.FinalResult;
import project.dlss.entity.Review;
import project.dlss.entity.User;
import project.dlss.exception.BadRequestException;
import project.dlss.exception.ResourceNotFoundException;
import project.dlss.repository.AnnotationRepository;
import project.dlss.repository.FinalResultRepository;
import project.dlss.repository.ReviewRepository;
import project.dlss.repository.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final AnnotationRepository annotationRepository;
    private final FinalResultRepository finalResultRepository;
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
            createFinalResult(annotation, reviewer);
        } else {
            annotation.setStatus("REJECTED");
        }

        annotationRepository.save(annotation);

        return review;
    }

    @Override
    @Transactional
    public Review updateReview(Long reviewId, String status, String comment) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        Review.ReviewStatus reviewStatus;
        try {
            reviewStatus = Review.ReviewStatus.valueOf(status);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new BadRequestException("Invalid review status: " + status + ", expected: APPROVED or REJECTED");
        }

        review.setStatus(reviewStatus);
        if (comment != null) review.setComment(comment);
        review.setReviewedAt(LocalDateTime.now());
        reviewRepository.save(review);

        Annotation annotation = review.getAnnotation();
        if (reviewStatus == Review.ReviewStatus.APPROVED) {
            annotation.setStatus("APPROVED");
            createFinalResult(annotation, review.getReviewer());
        } else {
            annotation.setStatus("REJECTED");
        }
        annotationRepository.save(annotation);

        return review;
    }

    private void createFinalResult(Annotation annotation, User decidedBy) {
        if (finalResultRepository.findByDataItem_Id(annotation.getDataItem().getId()).isPresent()) {
            return;
        }
        FinalResult fr = new FinalResult();
        fr.setDataItem(annotation.getDataItem());
        fr.setAnnotation(annotation);
        fr.setDecidedBy(decidedBy);
        fr.setDecidedAt(LocalDateTime.now());
        finalResultRepository.save(fr);
    }
}