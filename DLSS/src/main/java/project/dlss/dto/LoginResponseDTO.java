package project.dlss.dto;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private Long id;
    private String username;
    private String role;
    private String message;

}