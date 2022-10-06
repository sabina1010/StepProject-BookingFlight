package booking.az.controllers;

import booking.az.entities.User;
import booking.az.services.UserService;

import java.util.List;

public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    public User getUser(int id) {
        return service.getUser(id);
    }

    public boolean login(String userName, String password) {
        return service.login(userName, password);
    }

    public boolean register(User user) {
        return service.register(user);
    }

    public boolean deleteUser(int id) {
        return service.deleteUser(id);
    }


}
