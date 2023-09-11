package com.nisum.users.infrastructure.api.controller;

import com.nisum.users.aplication.services.UserService;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserCreatedDTO> createUser(@RequestBody UserCreateDTO user) {
        return ResponseEntity.ok(service.createUser(user));
    }
}
