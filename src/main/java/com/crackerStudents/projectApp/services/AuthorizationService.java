package com.crackerStudents.projectApp.services;

import com.crackerStudents.projectApp.dao.UserRepository;
import com.crackerStudents.projectApp.domain.User;
import com.crackerStudents.projectApp.dto.UserDTO;
import com.crackerStudents.projectApp.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

  //  private final UserMapper userMapper;

   // private final BCryptPasswordEncoder bCryptPasswordEncoder;

   // public void save(UserDTO userDTO) {
   //     userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
   //     userRepository.save(userMapper.userDtoToUser(userDTO));
   // }

    public void delete(UserDTO userDTO) {
  //      userRepository.delete(userMapper.userDtoToUser(userDTO));
    }

    public void update(UserDTO user) {
   //     User oldUser = userRepository.findByEmail(user.getEmail());
   //     if (!user.getPhone().isEmpty()) {
   //         oldUser.setPhone(user.getPhone());
   //     }
        if (!user.getPassword().isEmpty()) {
  //          oldUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
   //     userRepository.save(oldUser);

    }
}
