package ru.kata.spring.boot_security.demo.DAO;






import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {


    public void add(User user);
    public List<User> getList();
    public User getUser(Long id);
    public void deleteUser(Long id);
    public void editUser(User user);

   public UserDetails getUser(String username);
}
