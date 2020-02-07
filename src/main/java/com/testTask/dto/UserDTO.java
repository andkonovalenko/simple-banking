package com.testTask.dto;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserDTO {
    String password;

    @Email
    String mail;
}
