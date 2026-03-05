package project.dlss.service;

import project.dlss.dto.CreateUserRequest;
import project.dlss.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO createUser(CreateUserRequest request);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    void deleteUser(Long id);

}