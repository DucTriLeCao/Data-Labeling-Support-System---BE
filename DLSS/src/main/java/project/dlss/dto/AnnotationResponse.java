package project.dlss.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AnnotationResponse {
    private Long id;
    private Long dataItemId;
    private Long userId;
    private String labelValue;
    private String status;
    private Integer version;
    private LocalDateTime createdAt;
}
