package com.fixtab.user.mapper;

import java.util.List;

import com.fixtab.user.model.User;
import com.fixtab.user.model.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper {
    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);

    default User fromId (Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}
