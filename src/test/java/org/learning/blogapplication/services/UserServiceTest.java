package org.learning.blogapplication.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.learning.blogapplication.entities.dto.UserDto;
import org.learning.blogapplication.entities.models.User;
import org.learning.blogapplication.repositries.UserRepository;
import org.learning.blogapplication.services.implementation.UserServiceImple;
import org.learning.blogapplication.services.interfaces.UserService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImple userService;


    @BeforeEach
    void setUp() {
        ModelMapper modelMapper  = new ModelMapper();
        ReflectionTestUtils.setField(userService, "modelMapper", modelMapper);
    }

    @Test
    public void getUser()
    {
        int id = 85;
        User user = new User();
        user.setId(85);
        UserDto userDto = new UserDto();
        userDto.setId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserDto user1 = userService.findUser(id);
        assertEquals(user.getId(),user1.getId());

    }
}
