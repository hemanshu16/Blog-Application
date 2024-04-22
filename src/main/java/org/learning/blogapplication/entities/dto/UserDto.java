package org.learning.blogapplication.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.learning.blogapplication.entities.models.Role;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@ToString
public class UserDto {


    private int Id;

    @Size(max = 3)
    private String name;
    @Email
    private String email;
    @Size(min = 8)
    private String password;
    @Size(min = 5)
    private String about;

    private Set<Role> roles = new HashSet<>();
}
