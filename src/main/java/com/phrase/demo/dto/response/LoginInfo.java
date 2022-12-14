package com.phrase.demo.dto.response;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginInfo {
    private User user;
    private String token;
    private Timestamp expires;
    private Timestamp lastInvalidateAllSessionsPerformed;
}
