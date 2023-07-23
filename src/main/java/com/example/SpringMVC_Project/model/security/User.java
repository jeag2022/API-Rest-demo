package com.example.SpringMVC_Project.model.security;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_application")
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_authority", joinColumns = @JoinColumn( name="authority_id"), inverseJoinColumns = @JoinColumn( name="user_id"))
    private Set<Authority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public User() {
        super();
        authorities = new HashSet<>();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
}
