package org.learning.blogapplication.entities.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Entity

@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(length = 1024)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="User_Roles",
    joinColumns = @JoinColumn(name="userId",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="roldId",referencedColumnName = "roleId"))
    private Set<Role> roles = new HashSet<>();



    public void addRole(Role role)
    {
        this.roles.add(role);
    }
}
