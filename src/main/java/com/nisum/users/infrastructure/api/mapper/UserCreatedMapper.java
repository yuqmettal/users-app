package com.nisum.users.infrastructure.api.mapper;

import com.nisum.users.domain.model.User;
import com.nisum.users.infrastructure.api.dto.UserCreatedDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreatedMapper {
    @Mapping(target = "created", source = "createdAt")
    @Mapping(target = "modified", source = "lastModified")
    @Mapping(target = "last_login", source = "lastLogin")
    @Mapping(target = "isactive", source = "active")
    @Mapping(target = "token", ignore = true)
    UserCreatedDTO domainToDto(User user);
}