package project.dlss.dto;

import lombok.Data;

@Data
public class ExportRequest {
    private Long projectId;
    private Long datasetId;
    private String format; // json, csv
}
