package ru.vsu.cs.boldyrev.shopik.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vsu.cs.boldyrev.shopik.dto.auth.JwtResponseDTO;
import ru.vsu.cs.boldyrev.shopik.dto.auth.SendSMSNotificationDTO;
import ru.vsu.cs.boldyrev.shopik.dto.auth.SmsCodeDTO;

import java.util.Objects;

@Service
public class AuthService {

    private final RedisService redisService;
    private final int SMS_TTL = 120;

    public AuthService(RedisService redisService) {this.redisService = redisService;}

    public ResponseEntity<String> sendSMSNotification(SendSMSNotificationDTO dto) {
        //здесь должно быть api sms-уведомлений
        String smsCode = "0000";
        redisService.saveString(dto.getPhone(), smsCode, SMS_TTL);
        return ResponseEntity.status(200).body("The SMS was sent");
    }

    public JwtResponseDTO verifySMSCode(SmsCodeDTO dto) {
        String code = redisService.getString(dto.getPhone());
        if (Objects.equals(code, dto.getUserCode())) {
            redisService.deleteKey(dto.getPhone());
            //Дописать
        }
        return new JwtResponseDTO("", "");
    }
}
