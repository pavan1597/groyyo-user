package com.groyyo2.user.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class UserRequest {
    private Long id;
    private String emailId;
    private String phone;
    private String firstName;
    private String fullName;
}
