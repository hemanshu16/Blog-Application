package org.learning.blogapplication.services.interfaces;

import org.learning.blogapplication.entities.dto.UserDto;
import org.learning.blogapplication.entities.models.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDTO);

    UserDto updateUser(UserDto userDTO, int id);

    UserDto findUser(int userId);

    List<UserDto> getAllUser();

    void deleteUser(int userId);

    User getUserByUserName(String username);

    UserDto UserDtofromUserDetails(UserDetails userDetails);



}
