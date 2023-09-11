package com.nisum.users.infrastructure.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@NoArgsConstructor
public class UserCreateDTO {
    private String name;
    private String email;
    private String password;
    @NonNull
    private String token;
    private List<PhoneCreateDTO> phones;

    @Data
    @NoArgsConstructor
    public static class PhoneCreateDTO {
        private String number;
        // TODO: change citycode and to contrycode camel case
        private String citycode;
        private String contrycode;
    }
}
