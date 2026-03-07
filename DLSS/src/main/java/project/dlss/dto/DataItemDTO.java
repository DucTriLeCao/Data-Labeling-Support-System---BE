package project.dlss.dto;

import lombok.Data;

@Data
public class DataItemDTO {

    private Long datasetId;   
    private String dataUrl;  
    private String label;     
    private String status;    
}