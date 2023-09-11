package com.nisum.users.application.services;

import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import com.nisum.users.domain.model.User;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

public class UserTestFactory {

    public static final String VALID_EMAIL = "myuquilima@dominio.cl";
    public static final String VALID_NAME = "Juan Perez";
    public static final String VALID_PASSWORD = "X80@pass123";
    public static final String DUMMY_TOKEN = "dummy token";

    public static User createUser() {
        User user = new User();
        user.setToken(DUMMY_TOKEN);
        user.setCreatedAt(Timestamp.from(java.time.Instant.now()));
        user.setLastModified(Timestamp.from(java.time.Instant.now()));
        user.setLastLogin(Timestamp.from(java.time.Instant.now()));
        user.setActive(true);
        user.setName(VALID_NAME);
        return user;
    }

    public static UserCreateDTO createUserDTO() {
        UserCreateDTO.PhoneCreateDTO phone = new UserCreateDTO.PhoneCreateDTO(
                "123456",
                "789",
                "101112"
        );
        List<UserCreateDTO.PhoneCreateDTO> phones = Arrays.asList(phone);
        return new UserCreateDTO(VALID_NAME, VALID_EMAIL, VALID_PASSWORD, DUMMY_TOKEN, phones);
    }
}
