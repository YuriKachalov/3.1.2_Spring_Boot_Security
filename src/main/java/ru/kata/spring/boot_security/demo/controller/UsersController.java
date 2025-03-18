package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;


    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String sayHello(Principal principal, Model model) {
        String userName = principal.getName();
        User user = userService.getUserByName(userName);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String showUser(Model model) {
        List<User> user = userService.listUsers();
        model.addAttribute("users", user);
        return "users";
    }

//    @GetMapping("/user-create")
//    public String createUserForm(User user) {
//
//        return "user-create";
//    }
//
//    @PostMapping("/user-create")
//    public String createUser(User user) {
//        Role role = new Role();
//        role.setRole(user.getRoleSet().toString());
//        List<Role> roles = new ArrayList<>();
//        roles.add(role);
//        userService.saveUser(user, roles);
//        return "redirect:/admin";
//    }

    @GetMapping("/user-create")
    public String createUserForm(User user, Model model) {
        model.addAttribute("roles", userService.listRoles());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute User user, @RequestParam List<Integer> roles) {
        List<Role> roleEntities = userService.findRolesByIds(roles); // Получите роли по их ID
        userService.saveUser(user, roleEntities);
        return "redirect:/admin";
    }

    @GetMapping("/user-delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update")
    public String сhangeUserId(@RequestParam("id") int id, Model model) {
        User user = userService.getUserId(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String сhangeUser(User user, List<Role> roles) {
        userService.saveUser(user, roles);
        return "redirect:/admin";
    }

}
