package project.dlss.dto;

import lombok.Data;

@Data
public class LabelRequestDTO {

    private Long projectId;
    private String name;
    private Long parentId;

}