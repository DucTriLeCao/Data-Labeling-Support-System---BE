package project.dlss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnnotationRequest {
    @NotNull
    private Long dataItemId;

    @NotNull
    private Long userId;

    @NotBlank(message = "labelValue is required")
    private String labelValue; // JSON string
}
