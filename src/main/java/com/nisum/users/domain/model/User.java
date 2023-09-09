package com.nisum.users.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones;
}

