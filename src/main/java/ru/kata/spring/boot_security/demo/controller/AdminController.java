package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;



@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getUser(Model model) {
        model.addAttribute("userList", userService.getList());
        return "user_admin";
    }

    @GetMapping("/newAddUserAdmin")
    public String addNewUser(Model model) {
      User user = new User();
       model.addAttribute("user", user);

       List<Role> roles = roleService.getList();
        model.addAttribute("roleList", roles);

        return "user_new_admin";
    }

    @PostMapping("/newAddUserAdmin")
    public String saveNewUser(
            @ModelAttribute("user") User user
            ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/editUser/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {

        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roleList",roleService.getList());
        return "user_edit_admin";
    }

    @PatchMapping("/{id}")
    public String userSaveEdit(@PathVariable("id") Long id, @ModelAttribute("user") User user) {
        userService.editUser(user);
        return "redirect:/admin";
    }
}
