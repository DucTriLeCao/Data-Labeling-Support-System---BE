package project.dlss.dto;

import lombok.Data;

@Data
public class DatasetDTO {

    private String name;       
    private String description; 
    private Long projectId;    
    private String status;      
}