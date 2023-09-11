package com.nisum.users.infrastructure.api.mapper;

import com.nisum.users.application.services.UserTestFactory;
import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserCreateMapperTest {

    private final UserCreateMapper mapper = Mappers.getMapper(UserCreateMapper.class);

    @Test
    void testDtoToDomainMapping() {
        UserCreateDTO userCreateDTO = UserTestFactory.createUserDTO();

        User user = mapper.dtoToDomain(userCreateDTO);

        assertEquals(user.getEmail(), userCreateDTO.getEmail());
        assertEquals(user.getName(), userCreateDTO.getName());
        assertEquals(user.getPassword(), userCreateDTO.getPassword());
        assertEquals(user.getUsersPhones().size(), userCreateDTO.getPhones().size());
    }
}
