package project.dlss.dto;

import lombok.Data;

@Data
public class ReviewRequestDTO {
    private Long annotationId;

    private Long reviewerId;

    private String status;

    private String comment;
}

