package project.dlss.dto;

import lombok.Data;

@Data
public class CreateActivityLogRequest {

    private Long userId;
    private String action;
    private String entityType;
    private Long entityId;

}