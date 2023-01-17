package ru.kata.spring.boot_security.demo.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {


    private final RoleDaoImpl roleDao;

    @Autowired

    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }


    public boolean addRole(Role role) {
        Role userBas = roleDao.findByName(role.getRole());
        if (userBas != null) {
            return false;
        }
        roleDao.add(role);
        return true;
    }

    public Role findByNameRole(String name) {
        return roleDao.findByName(name);
    }

    public List<Role> listRoles() {
        return roleDao.listRoles();
    }

    public Role findByIdRole(Long id) {
        return roleDao.findByIdRole(id);
    }

    public List<Role> listByRole(List<String> name) {
        return roleDao.listByName(name);
    }
}
