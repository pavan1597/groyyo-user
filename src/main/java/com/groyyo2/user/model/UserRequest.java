package com.groyyo2.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class UserRequest {
    private Long id;
    private String emailId;
    private String phone;
    private String firstName;
    private String fullName;
}
