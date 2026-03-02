package project.dlss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.dlss.dto.ReviewRequest;
import project.dlss.entity.Review;
import project.dlss.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> review(@Valid @RequestBody ReviewRequest request) {

        Review review = reviewService.reviewAnnotation(
                request.getAnnotationId(),
                request.getReviewerId(),
                request.getStatus(),
                request.getComment()
        );

        return ResponseEntity.ok(review);
    }
}