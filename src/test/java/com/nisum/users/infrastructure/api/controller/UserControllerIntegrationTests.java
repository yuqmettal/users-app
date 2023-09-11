package com.nisum.users.infrastructure.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.users.aplication.services.UserService;
import com.nisum.users.application.services.UserTestFactory;
import com.nisum.users.domain.exceptions.EmailAlreadyExistsException;
import com.nisum.users.domain.exceptions.InvalidEmailFormatException;
import com.nisum.users.domain.exceptions.InvalidPasswordFormatException;
import com.nisum.users.infrastructure.api.dto.ErrorDTO;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTests {
    public static final String USERS_PATH = "/users";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Value("${error.invalidEmailFormat}")
    private String invalidEmailFormat;

    @Value("${error.emailAlreadyExists}")
    private String emailAlreadyExists;

    @Value("${error.invalidPasswordFormat}")
    private String invalidPasswordFormat;

    @Test
    public void testCreateUser() throws Exception {
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        UserCreatedDTO userCreatedDTO = new UserCreatedDTO();

        when(userService.createUser(any(UserCreateDTO.class))).thenReturn(userCreatedDTO);

        MockHttpServletRequestBuilder postRequest = post(USERS_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDTO));
        mockMvc.perform(postRequest)
                .andExpect(status().isCreated())
                .andExpect(content().json(asJsonString(userCreatedDTO)));
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testInvalidEmailException() throws Exception {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje(invalidEmailFormat);
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        when(userService.createUser(any(UserCreateDTO.class))).thenThrow(new InvalidEmailFormatException());

        mockMvc.perform(post(USERS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(asJsonString(error)));
    }

    @Test
    public void testInvalidPasswordException() throws Exception {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje(invalidPasswordFormat);
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        when(userService.createUser(any(UserCreateDTO.class))).thenThrow(new InvalidPasswordFormatException());

        mockMvc.perform(post(USERS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(asJsonString(error)));
    }

    @Test
    public void testEmailAlreadyExistsException() throws Exception {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje(emailAlreadyExists);
        UserCreateDTO userDTO = UserTestFactory.createUserDTO();
        when(userService.createUser(any(UserCreateDTO.class))).thenThrow(new EmailAlreadyExistsException(userDTO.getEmail()));

        mockMvc.perform(post(USERS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(content().json(asJsonString(error)));
    }

    @Test
    public void testInvalidRequestBody() throws Exception {
        UserCreateDTO userDTO = new UserCreateDTO();

        mockMvc.perform(post(USERS_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDTO)))
                .andExpect(status().isBadRequest());
    }
}
