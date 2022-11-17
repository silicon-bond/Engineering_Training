package edu.example.express.entity;

import lombok.Data;

@Data
public class RegisterUserBean {
    String PhoneNum;
    String email;
    String password;
    String captcha;
}
