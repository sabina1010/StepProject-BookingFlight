package booking.az.controllers;

import booking.az.dao.impl.UserDAO;
import booking.az.database.Database;
import booking.az.entities.User;
import booking.az.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private UserController userController;
    private User testUser;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        UserDAO userDAO = new UserDAO(database);
        UserService userService = new UserService(userDAO);
        userController = new UserController(userService);
        database.init();
        testUser = new User(3, "someone", "8772");
    }

    @Test
    void testGetAllUsers() {
        userController.register(testUser);
        assertEquals(2, userController.getAllUsers().size());
    }

    @Test
    void testGetUserById() {
        userController.register(testUser);
        assertEquals(testUser, userController.getUser(3));
    }

    @Test
    void testRegistration() {
        assertTrue(userController.register(testUser));
    }


    @Test
    void testLogin() {
        userController.register(testUser);
        assertTrue(userController.login("someone", "8772"));
    }


    @Test
    void testDeletingRegistrationById() {
        userController.register(testUser);
        assertTrue(userController.deleteUser(3));
    }


}