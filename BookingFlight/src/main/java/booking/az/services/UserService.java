package booking.az.services;

import booking.az.dao.impl.UserDAO;
import booking.az.entities.User;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDAO dao;

    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public List<User> getAllUsers() {
        return dao.getAll();
    }

    public User getUser(int id) {
        return dao.get(id);
    }

    public boolean login(String userName, String password) {
        Optional<User> find = dao.getAll().stream()
                .filter(user -> user.getUsername().equals(userName) && user.getPassword().equals(password))
                .findFirst();
        return find.isPresent();
    }

    public boolean register(User user) {
        Optional<User> find = dao.getAll().stream()
                .filter(x -> x.getUsername().equals(user.getUsername()))
                .findFirst();
        if (find.isPresent()) return false;
        return dao.create(user);
    }

    public boolean deleteUser(int id) {
        return dao.delete(id);
    }

}
