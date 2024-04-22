package org.learning.blogapplication.services.implementation;

import lombok.Setter;
import org.learning.blogapplication.entities.models.CustomeUserDetails;
import org.learning.blogapplication.entities.models.User;
import org.learning.blogapplication.exceptions.ResourceNotFound;
import org.learning.blogapplication.repositries.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFound("User","email " + username,0));
        CustomeUserDetails customeUserDetails = new CustomeUserDetails(user);
        return customeUserDetails;
    }
}
