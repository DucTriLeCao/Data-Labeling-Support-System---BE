package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.ReviewRequestDTO;
import project.dlss.entity.Review;
import project.dlss.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public Review reviewAnnotation(@RequestBody ReviewRequestDTO dto) {
        return reviewService.reviewAnnotation(dto);
    }
}