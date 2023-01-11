package com.groyyo.user.model;

import lombok.*;

@Data
public class UserDTO {

    private Long id;
    private String emailId;
    private String phone;
    private String firstName;
    private String fullName;
}
