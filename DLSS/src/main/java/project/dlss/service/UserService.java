package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.UserRequestDTO;
import project.dlss.dto.UserResponseDTO;
import project.dlss.entity.User;
import project.dlss.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(dto.getPassword()); 
        user.setRole(User.Role.valueOf(dto.getRole()));
        user.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(user);

        return mapToResponse(saved);
    }

    public List<UserResponseDTO> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public UserResponseDTO login(String username, String password) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPasswordHash().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return mapToResponse(user);
    }

    private UserResponseDTO mapToResponse(User user) {

        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        dto.setStatus(user.getStatus());

        return dto;
    }
}