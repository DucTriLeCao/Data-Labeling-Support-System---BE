package project.dlss.dto;

import lombok.Data;
import project.dlss.entity.User.Role;

@Data
public class UserDTO {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private String status;

}