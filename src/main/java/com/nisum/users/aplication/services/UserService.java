package com.nisum.users.aplication.services;

import com.nisum.users.domain.exceptions.EmailAlreadyExistsException;
import com.nisum.users.domain.exceptions.InvalidEmailFormatException;
import com.nisum.users.domain.exceptions.InvalidPasswordFormatException;
import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import com.nisum.users.infrastructure.api.mapper.UserCreateMapper;
import com.nisum.users.infrastructure.api.mapper.UserCreatedMapper;
import com.nisum.users.infrastructure.persistence.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCreateMapper userCreateMapper;
    @Autowired
    private UserCreatedMapper userCreatedMapper;
    @Autowired
    private JWTService jwtService;

    @Value("${email.regex}")
    private String EMAIL_REGEX;

    @Value("${password.regex}")
    private String PASSWORD_REGEX;

    public UserCreatedDTO createUser(UserCreateDTO userDTO) {
        validate_input_data(userDTO);

        User userToCreate = userCreateMapper.dtoToDomain(userDTO);
        userToCreate.getUsersPhones().forEach(phone -> phone.setUser(userToCreate));
        userToCreate.setToken(jwtService.createToken(userDTO.getEmail()));
        User createdUser = userRepository.save(userToCreate);
        return userCreatedMapper.domainToDto(createdUser);
    }

    private void validate_input_data(UserCreateDTO userDTO) {
        if (!userDTO.getEmail().matches(EMAIL_REGEX)) {
            throw new InvalidEmailFormatException();
        }
        if (!userDTO.getPassword().matches(PASSWORD_REGEX)) {
            throw new InvalidPasswordFormatException();
        }
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException(userDTO.getEmail());
        }
    }
}
