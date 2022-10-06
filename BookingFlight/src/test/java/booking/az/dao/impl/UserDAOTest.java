package booking.az.dao.impl;

import booking.az.database.Database;
import booking.az.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    private UserDAO userDAO;
    private User user;
    private User testUser;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        userDAO = new UserDAO(database);
        database.init();
        user = database.getUsers().get(0);
        testUser = new User(3, "someone", "8772");
    }

    @Test
    void testGetAll() {
        userDAO.create(testUser);
        assertEquals(2, userDAO.getAll().size());

    }

    @Test
    void testGetById() {
        assertEquals(user, userDAO.get(1));
    }

    @Test
    void testCreatingUser() {
        userDAO.create(testUser);
        assertEquals(testUser, userDAO.get(3));
    }

    @Test
    void testDeletingByReference() {
        userDAO.create(testUser);
        userDAO.delete(testUser);
        assertEquals(1, userDAO.getAll().size());
    }

    @Test
    void testDeletingById() {
        userDAO.create(testUser);
        assertTrue(userDAO.delete(3));
    }
}