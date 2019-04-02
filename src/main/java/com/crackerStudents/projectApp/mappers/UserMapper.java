package com.crackerStudents.projectApp.mappers;

import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO userToUserDto(User user);

    List<UserDTO> usersToUsersDto(List<User> users);

    User userDtoToUser(UserDTO user);

    List<User> usersDtoToUsers(List<UserDTO> users);
}
