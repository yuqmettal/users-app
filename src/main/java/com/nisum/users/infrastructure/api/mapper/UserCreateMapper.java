package com.nisum.users.infrastructure.api.mapper;

import com.nisum.users.domain.model.User;
import com.nisum.users.domain.model.UsersPhones;
import com.nisum.users.infrastructure.api.dto.UserCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserCreateMapper {

    String TIMESTAMP_EXPRESSION = "java(java.sql.Timestamp.from(java.time.Instant.now()))";
    String UUID_EXPRESSION = "java(java.util.UUID.randomUUID())";

    @Mapping(target = "createdAt", expression = TIMESTAMP_EXPRESSION)
    @Mapping(target = "lastModified", expression = TIMESTAMP_EXPRESSION)
    @Mapping(target = "active", constant = "true")
    @Mapping(target = "lastLogin", expression = TIMESTAMP_EXPRESSION)
    @Mapping(target = "id", expression = UUID_EXPRESSION)
    @Mapping(target = "usersPhones", source = "phones")
    User dtoToDomain(UserCreateDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cityCode", source = "citycode")
    @Mapping(target = "countryCode", source = "contrycode")
    UsersPhones dtoToPhone(UserCreateDTO.PhoneCreateDTO phoneDTO);

}
