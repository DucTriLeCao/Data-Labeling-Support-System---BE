package project.dlss.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.dlss.dto.LoginRequestDTO;
import project.dlss.dto.LoginResponseDTO;
import project.dlss.dto.RegisterRequestDTO;
import project.dlss.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // LOGIN
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }

    // REGISTER
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO request) {
        return authService.register(request);
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout() {
        return authService.logout();
    }
}