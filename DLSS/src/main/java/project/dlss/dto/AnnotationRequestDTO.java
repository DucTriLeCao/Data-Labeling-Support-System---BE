package project.dlss.dto;

import lombok.Data;

@Data
public class AnnotationRequestDTO {
    private Long dataItemId;

    private Long userId;

    private Long labelId;

    private String labelValue;
}
