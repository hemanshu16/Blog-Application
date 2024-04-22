package org.learning.blogapplication.services.implementation;

import org.learning.blogapplication.entities.models.Role;
import org.learning.blogapplication.entities.models.User;
import org.learning.blogapplication.entities.dto.UserDto;
import org.learning.blogapplication.exceptions.ResourceNotFound;
import org.learning.blogapplication.repositries.RoleRepository;
import org.learning.blogapplication.repositries.UserRepository;
import org.learning.blogapplication.security.JwtTokenHelper;
import org.learning.blogapplication.services.interfaces.PostService;
import org.learning.blogapplication.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImple implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDto createUser(UserDto userDTO) {
        User user = this.userDTOTouser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userrole = roleRepository.findById(1).get();

        user.addRole(userrole);
        User savedUser = userRepository.save(user);
        return this.userTouserDTO(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDTO, int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "id", userId));

        user.setAbout(userDTO.getAbout());
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        User updatedUser = userRepository.save(user);
        return this.userTouserDTO(updatedUser);
    }

    @Override
    public UserDto findUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "id", userId));
        return this.userTouserDTO(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userTouserDTO(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("user", "id", userId));
        userRepository.delete(user);
    }

    @Override
    public User getUserByUserName(String username) {
        return userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFound("user","email :- " + username,0));
    }

    @Override
    public UserDto UserDtofromUserDetails(UserDetails userDetails) {
        return this.modelMapper.map((User)userDetails,UserDto.class);
    }

    public UserDto userTouserDTO(User user) {
        UserDto userDTO = this.modelMapper.map(user, UserDto.class);
//        userDTO.setId(user.getId());
//        userDTO.setName(user.getName());
//        userDTO.setEmail(user.getEmail());
//        userDTO.setPassword(user.getPassword());
//        userDTO.setAbout(user.getAbout());
        return userDTO;
    }

    public User userDTOTouser(UserDto userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
//        user.setId(userDTO.getId());
//        user.setAbout(userDTO.getAbout());
//        user.setEmail(userDTO.getEmail());
//        user.setName(userDTO.getName());
//        user.setPassword(userDTO.getPassword());
        return user;
    }
}
