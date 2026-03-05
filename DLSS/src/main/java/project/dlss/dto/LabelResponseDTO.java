package project.dlss.dto;

import lombok.Data;

@Data
public class LabelResponseDTO {

    private Long id;
    private String name;
    private Long projectId;
    private Long parentId;

}