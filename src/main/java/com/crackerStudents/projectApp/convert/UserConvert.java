package com.crackerStudents.projectApp.convert;

import com.crackerStudents.projectApp.DTO.UserDTO;
import com.crackerStudents.projectApp.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * @Author Krylov Sergey
 */
public class UserConvert {
    @Autowired
    ModelMapper modelMapper;

    private UserDTO convertToDto(User user) {
        UserDTO packDTO = modelMapper.map(user, UserDTO.class);
        return packDTO;
    }


    private User convertToEntity(UserDTO userDTO) throws ParseException {
        User pack = modelMapper.map(userDTO, User.class);
        return pack;
    }
}
