package project.dlss.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AiSuggestRequest {
    @NotNull
    private Long dataItemId;

    @NotBlank
    private String content;
}
