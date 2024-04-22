package org.learning.blogapplication.entities.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class CustomeUserDetails extends User implements UserDetails {


    public CustomeUserDetails(User user)
    {
        super.setId(user.getId());
        super.setAbout(user.getAbout());
        super.setComments(user.getComments());
        super.setName(user.getName());
        super.setEmail(user.getEmail());
        super.setPassword(user.getPassword());
        super.setRoles(user.getRoles());
        super.setPosts(user.getPosts());
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = super.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//        super.getRoles().stream().map(role -> role.getName()).forEach(System.out::println);
        return authorities;
    }

    @Override
    public String getUsername() {
        return super.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
