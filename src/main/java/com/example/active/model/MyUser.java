package com.example.active.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor @NoArgsConstructor @Data
@Entity
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    @NotEmpty(message = "name can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String name;
    @NotEmpty(message = "username can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String username;
    @NotEmpty(message = "password can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @NotEmpty(message = "email can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String email;
    //---------------------------------------chan
    @NotEmpty(message = "role can't be empty!")
    @Column(columnDefinition = "varchar(255) not null")
    private String role;

    //ممكن شخص ماعنده
    private Integer clubID;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
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
