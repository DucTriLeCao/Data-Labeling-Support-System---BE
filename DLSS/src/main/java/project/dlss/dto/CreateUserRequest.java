package project.dlss.dto;

import lombok.Data;
import project.dlss.entity.User.Role;

@Data
public class CreateUserRequest {

    private String username;
    private String email;
    private String password;
    private Role role;

}