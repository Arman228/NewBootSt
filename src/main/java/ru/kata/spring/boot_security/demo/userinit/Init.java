package ru.kata.spring.boot_security.demo.userinit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.dao.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.dao.service.UserServiceImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Init {


    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public Init(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        roleService.addRole(role1);
        roleService.addRole(role2);

        List<Role> roleAdmin = new ArrayList<>();
        List<Role> roleUser = new ArrayList<>();

        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("admin", "admin", "ad@min", roleAdmin);
        User user2 = new User("user", "user", "us@er", roleUser);

        userService.add(user1);
        userService.add(user2);
    }
}
