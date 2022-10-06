package booking.az.services;

import booking.az.dao.impl.UserDAO;
import booking.az.database.Database;
import booking.az.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;
    private User user;
    private User testUser;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        UserDAO userDAO = new UserDAO(database);
        userService = new UserService(userDAO);
        user = database.getUsers().get(0);
        testUser = new User(3, "someone", "8772");
    }

    @Test
    void testGetAllUsers() {
        userService.register(testUser);
        assertEquals(2, userService.getAllUsers().size());
    }

    @Test
    void testGetById() {
        assertEquals(user, userService.getUser(1));
    }

    @Test
    void testRegistration() {
        assertTrue(userService.register(testUser));
    }

    @Test
    void testLogin() {
        userService.register(testUser);
        assertTrue(userService.login("someone", "8772"));
    }

    @Test
    void testInvalidLogin() {
        assertFalse(userService.login("someone", "8772"));
    }

    @Test
    void testDeletingUserById() {
        userService.register(testUser);
        assertTrue(userService.deleteUser(3));
    }


}