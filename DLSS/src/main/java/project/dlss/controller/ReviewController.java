package project.dlss.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.ReviewRequest;
import project.dlss.entity.Review;
import project.dlss.service.ReviewService;

import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewRequest request) {
        Review review = reviewService.reviewAnnotation(
                request.getAnnotationId(),
                request.getReviewerId(),
                request.getStatus(),
                request.getComment()
        );
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        String status = body.get("status");
        String comment = body.get("comment");
        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        Review review = reviewService.updateReview(id, status, comment);
        return ResponseEntity.ok(review);
    }
}