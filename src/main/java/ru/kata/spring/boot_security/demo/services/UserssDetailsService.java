package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
//import ru.kata.spring.boot_security.demo.security.UserDetailsImp;

import java.util.Optional;
//
//@Service
//public class UserssDetailsService implements UserDetailsService {
//    private final UserRepository userRepository;
//
//    @Autowired
//    public UserssDetailsService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> userOptional = userRepository.findByName(username);
//        if (userOptional.isEmpty())
//            throw new UsernameNotFoundException("логин не верный");
//        return userOptional.get();
//    }
//}
