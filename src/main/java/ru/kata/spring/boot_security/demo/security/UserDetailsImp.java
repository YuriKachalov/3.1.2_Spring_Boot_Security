package ru.kata.spring.boot_security.demo.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
//
//public class UserDetailsImp implements UserDetails {
//    private final User user;
//
//    public UserDetailsImp(User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.user.getRoleSet();
//    }
//
//    @Override
//    public String getPassword() {
//        return this.user.getPasswordUser();
//    }

//    @Override
//    public String getUsername() {
//        return this.user.getName();
//    }

//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

//    public User getUser() {
//        return user;
//    }
//}
