package com.nisum.users.aplication.services;

import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import com.nisum.users.infrastructure.api.mapper.UserCreateMapper;
import com.nisum.users.infrastructure.api.mapper.UserCreatedMapper;
import com.nisum.users.infrastructure.persistence.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserCreateMapper userCreateMapper;
    @Autowired
    private UserCreatedMapper userCreatedMapper;

    public UserCreatedDTO createUser(UserCreateDTO userDTO) {
        User userToCreate = userCreateMapper.dtoToDomain(userDTO);
        userToCreate.getUsersPhones().forEach(phone -> phone.setUser(userToCreate));
        User createdUser = userRepository.save(userToCreate);
        return userCreatedMapper.domainToDto(createdUser);
    }
}
