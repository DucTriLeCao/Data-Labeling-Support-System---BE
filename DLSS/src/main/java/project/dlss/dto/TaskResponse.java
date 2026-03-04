package project.dlss.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private Long assignmentId;
    private Long dataItemId;
    private String content;
    private String status;
    private LocalDateTime assignedAt;
    private Long datasetId;
    private String datasetName;
    private Long projectId;
    private String projectName;
}
