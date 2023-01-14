package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@Component
public class Init {



        private UserService userService;
        private RoleService roleService;

    @Autowired
    public Init(UserService userService, RoleService roleService) {
            this.userService = userService;
            this.roleService = roleService;
        }


    //    @EventListener(ApplicationReadyEvent.class)
    @PostConstruct
        public void createTable () {
            if (roleService.getList().isEmpty()) {
                Role admin = new Role(1L, "ROLE_ADMIN");
                Role user = new Role(2L, "ROLE_USER");
                roleService.add(admin);
                roleService.add(user);
                List<Role> setRole = new ArrayList<>();
                setRole.add(admin);
                User newAdmin = new User("Alex", "Zer", 20,
                        "sea@yan.ru", "$2a$12$.T9dBWlhRkOVz9Qsm7jgV.rdkBW5AAaO0ewB1NQ/t7GNZYpnP7eqy", setRole);

                userService.add(newAdmin);

                System.out.println("hello, I have just create few users: \n" +
                        "username: Alex      password: 1 \n");
            }
        }
    }
