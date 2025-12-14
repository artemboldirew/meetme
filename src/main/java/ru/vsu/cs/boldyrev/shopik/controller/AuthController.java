package ru.vsu.cs.boldyrev.shopik.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.cs.boldyrev.shopik.dto.auth.JwtResponseDTO;
import ru.vsu.cs.boldyrev.shopik.dto.auth.SendSMSNotificationDTO;
import ru.vsu.cs.boldyrev.shopik.dto.auth.SmsCodeDTO;
import ru.vsu.cs.boldyrev.shopik.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    public final AuthService authService;

    public AuthController(AuthService authService) {this.authService = authService;}

    @PostMapping("/signin")
    public ResponseEntity<String> sendSMSNotification(SendSMSNotificationDTO dto) {
        return authService.sendSMSNotification(dto);
    }

    @PostMapping("/verify/phone")
    public JwtResponseDTO verifySMSCode(SmsCodeDTO dto) {
        return authService.verifySMSCode(dto);
    }
}
