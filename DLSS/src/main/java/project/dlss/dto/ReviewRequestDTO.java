package project.dlss.dto;

import jakarta.validation.constraints.NotNull;

public class ReviewRequest {
    @NotNull
    private Long annotationId;

    @NotNull
    private Long reviewerId;

    @NotNull
    private String status; // expects APPROVED or REJECTED

    private String comment;

    public Long getAnnotationId() { return annotationId; }
    public void setAnnotationId(Long annotationId) { this.annotationId = annotationId; }

    public Long getReviewerId() { return reviewerId; }
    public void setReviewerId(Long reviewerId) { this.reviewerId = reviewerId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}

