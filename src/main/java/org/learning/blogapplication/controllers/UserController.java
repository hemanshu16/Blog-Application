package org.learning.blogapplication.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.learning.blogapplication.entities.dto.UserDto;
import org.learning.blogapplication.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUser();
        return new ResponseEntity<>(userDtos, HttpStatus.ACCEPTED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<UserDto> getUserById(@AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(this.userService.UserDtofromUserDetails(userDetails), HttpStatus.ACCEPTED);
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto userDTO) {
        UserDto userDto1 = userService.createUser(userDTO);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO, @PathVariable Integer userId) {
        UserDto userDto1 = userService.updateUser(userDTO, userId);
        return ResponseEntity.ok(userDto1);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(Map.of("message", "User Deleted Successfully"));
    }
}
