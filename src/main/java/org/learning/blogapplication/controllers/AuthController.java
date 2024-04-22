package org.learning.blogapplication.controllers;

import org.learning.blogapplication.entities.ApiResponse;
import org.learning.blogapplication.entities.dto.AuthRequestDto;
import org.learning.blogapplication.entities.dto.JwtResponseDto;
import org.learning.blogapplication.entities.dto.UserDto;
import org.learning.blogapplication.security.JwtTokenHelper;
import org.learning.blogapplication.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenHelper jwtService;

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public JwtResponseDto AuthenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO){
        System.out.println("DOne done done \n\n");
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
        if(authentication.isAuthenticated()){
            return JwtResponseDto.builder()
                    .token(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
        } else {
            throw new UsernameNotFoundException("invalid user request..!!");
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse> createNewUser(@RequestBody UserDto userDto)
    {
        userService.createUser(userDto);
        return  new ResponseEntity<ApiResponse>(new ApiResponse("Registration Successfull","Success"), HttpStatus.CREATED);
    }

}
