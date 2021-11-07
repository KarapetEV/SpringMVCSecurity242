package web.service;

import web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUser(long id);

    public void addUser(User user);

    void saveUser(User user);

    void removeUser(long id);

    void updateUser(long id, User updatedUser);
}
