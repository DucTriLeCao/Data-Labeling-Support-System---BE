package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.LoginRequestDTO;
import project.dlss.dto.LoginResponseDTO;
import project.dlss.entity.User;
import project.dlss.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public LoginResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Wrong password");
        }

        LoginResponseDTO response = new LoginResponseDTO();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setRole(user.getRole().name());
        response.setMessage("Login successful");

        return response;
    }

    public String logout() {
        return "Logout successful";
    }
}