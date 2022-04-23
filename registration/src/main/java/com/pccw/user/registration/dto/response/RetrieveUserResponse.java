package com.pccw.user.registration.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RetrieveUserResponse {

    private int userId;

    private String email;

    private boolean isActive;
}
