package ru.kata.spring.boot_security.demo.dao.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService  {

    boolean addRole(Role role);
    Role findByNameRole(String name);
    List<Role> listRoles();
    Role findByIdRole(Long id);
    List<Role> listByRole(List<String> name);
}
