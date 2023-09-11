package com.nisum.users.infrastructure.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
public class UserCreatedDTO {
    private UUID id;
    private Timestamp created;
    private Timestamp modified;
    private Timestamp last_login;
    private String token;
    private String isactive;
}
