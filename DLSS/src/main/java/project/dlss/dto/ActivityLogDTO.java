package project.dlss.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivityLogDTO {

    private Long id;
    private Long userId;
    private String action;
    private String entityType;
    private Long entityId;
    private LocalDateTime createdAt;

}