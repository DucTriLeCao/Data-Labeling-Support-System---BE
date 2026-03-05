package project.dlss.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectOverviewDTO {

    private String projectName;
    private Long totalDatasets;
    private Long totalItems;

}