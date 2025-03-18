package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    //    удаляем user
    void deleteUser(int id);

    //    изменять пользователя
    void saveUser(User user, List<Role> roles);

    List<User> listUsers();

    //получаем доступ по id
    User getUserId(Integer id);

    User getUserByName(String userName);

    //методы для Role
    List<Role> listRoles();

    public Role saveRole(Role role);

    public List<Role> findRolesByIds(List<Integer> roleIds);
}
//    Role getRoleId(List <Integer> roles);
//Role getRoleByName(String userName);

