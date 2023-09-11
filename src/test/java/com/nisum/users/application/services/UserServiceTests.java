package com.nisum.users.application.services;

import com.nisum.users.aplication.services.JWTService;
import com.nisum.users.aplication.services.UserService;
import com.nisum.users.domain.exceptions.EmailAlreadyExistsException;
import com.nisum.users.domain.exceptions.InvalidEmailFormatException;
import com.nisum.users.domain.exceptions.InvalidPasswordFormatException;
import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import com.nisum.users.infrastructure.api.mapper.UserCreateMapper;
import com.nisum.users.infrastructure.api.mapper.UserCreatedMapper;
import com.nisum.users.infrastructure.persistence.jpa.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserCreateMapper userCreateMapper;

    @MockBean
    private UserCreatedMapper userCreatedMapper;

    @MockBean
    private JWTService jwtService;

    @Test
    public void shouldCreateUserSuccessfully() {
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        User user = UserTestFactory.createUser();
        UserCreatedDTO userCreatedDTO = new UserCreatedDTO();
        userCreatedDTO.setCreated(user.getCreatedAt());
        userCreatedDTO.setToken(user.getToken());

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userCreateMapper.dtoToDomain(userDTO)).thenReturn(user);
        when(userCreatedMapper.domainToDto(user)).thenReturn(userCreatedDTO);
        when(jwtService.createToken(userDTO.getEmail())).thenReturn(UserTestFactory.DUMMY_TOKEN);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserCreatedDTO result = userService.createUser(userDTO);

        assertTrue(result instanceof UserCreatedDTO);
        assertEquals(result.getToken(), user.getToken());
        assertEquals(result.getCreated(), user.getCreatedAt());
    }

    @Test
    public void shouldThrowInvalidEmailFormatException() {
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        userDTO.setEmail("invalid email");

        assertThrows(InvalidEmailFormatException.class, () -> {
            userService.createUser(userDTO);
        });
    }

    @Test
    public void shouldThrowInvalidPasswordFormatException() {
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        userDTO.setPassword("invalid password");

        assertThrows(InvalidPasswordFormatException.class, () -> {
            userService.createUser(userDTO);
        });
    }

    @Test
    public void shouldThrowEmailAlreadyExistsException() {
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();

        when(userRepository.existsByEmail(userDTO.getEmail())).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(userDTO);
        });
    }
}
