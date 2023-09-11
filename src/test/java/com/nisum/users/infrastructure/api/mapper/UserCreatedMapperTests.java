package com.nisum.users.infrastructure.api.mapper;

import com.nisum.users.application.services.UserTestFactory;
import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserCreatedMapperTest {

    private final UserCreatedMapper mapper = Mappers.getMapper(UserCreatedMapper.class);

    @Test
    void testDomainToDtoMapping() {
        User user = UserTestFactory.createUser();

        UserCreatedDTO dto = mapper.domainToDto(user);

        assertEquals(user.getCreatedAt(), dto.getCreated());
        assertEquals(user.getLastModified(), dto.getModified());
        assertEquals(user.getLastLogin(), dto.getLast_login());
        assertEquals(user.isActive(), dto.getIsactive().equals("true"));
    }
}
