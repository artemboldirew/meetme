package ru.vsu.cs.boldyrev.shopik.dto.auth;

import lombok.Getter;

@Getter
public class SmsCodeDTO {
    private String phone;
    private String userCode;
}
