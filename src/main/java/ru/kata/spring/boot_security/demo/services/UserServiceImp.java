package ru.kata.spring.boot_security.demo.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositori;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.util.*;

@Service
public class UserServiceImp implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepositori roleRepositori;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepositori roleRepositori) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepositori = roleRepositori;
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveUser(User user, List<Role> roles) {
        Optional<User> existingUserOpt = userRepository.findById(user.getId());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();
            existingUser.setName(user.getName());
            existingUser.setSurname(user.getSurname());
            existingUser.setAge(user.getAge());

            if (user.getPasswordUser() != null && !user.getPasswordUser().isEmpty()) {
                existingUser.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
            }

            Set<Role> existingRoles = new HashSet<>();
            for (Role role : roles) {
                existingRoles.add(saveRole(role));
            }
            existingUser.setRoleSet(existingRoles);

            userRepository.save(existingUser);
        } else {
            user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
            Set<Role> roleSet = new HashSet<>();
            for (Role role : roles) {
                roleSet.add(saveRole(role));
            }
            user.setRoleSet(roleSet);

            userRepository.save(user);
        }
    }


    @Override
    @Transactional
    public Role saveRole(Role role) {
        Optional<Role> existingRoleOpt = roleRepositori.findById(role.getId());

        if (existingRoleOpt.isPresent()) {
            Role existingRole = existingRoleOpt.get();
            existingRole.setRole(role.getRole());
             return roleRepositori.save(role);
        } else {
            return roleRepositori.save(role);
        }
    }

    @Override
    @Transactional
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserId(Integer id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    public User getUserByName(String userName) {
        User user = null;
        Optional<User> optionalUser = userRepository.findByName(userName);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    @Transactional
    public List<Role> listRoles() {
        return roleRepositori.findAll();
    }

    @Override
    @Transactional
    public List<Role> findRolesByIds(List<Integer> roleIds) {
        return roleRepositori.findAllByIdIn(roleIds);
    }



    //UserDetailsService
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByName(username);
        if (userOptional.isEmpty())
            throw new UsernameNotFoundException("логин не верный");
        return userOptional.get();
    }
}
