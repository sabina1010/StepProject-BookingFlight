package booking.az.dao.impl;

import booking.az.database.Database;
import booking.az.entities.Booking;
import booking.az.entities.Flight;
import booking.az.entities.Passenger;
import booking.az.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDAOTest {
    private BookingDAO bookingDAO;
    private User user;
    private Flight flight;
    private List<Passenger> passengers;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        bookingDAO = new BookingDAO(database);
        passengers = new ArrayList<>(List.of(new Passenger(1, "Sabina", "Ahmadova")));
        user = database.getUsers().get(0);
        flight = database.getFlights().get(0);
    }

    @Test
    void testGetAll() {
        Booking booking = new Booking(1, user, flight, passengers);
        bookingDAO.create(booking);
        assertEquals(1, bookingDAO.getAll().size());
    }

    @Test
    void testGetById() {
        Booking booking = new Booking(1, user, flight, passengers);
        bookingDAO.create(booking);
        assertEquals(booking, bookingDAO.get(1));
    }

    @Test
    void createTest() {
        Booking booking = new Booking(1, user, flight, passengers);
        bookingDAO.create(booking);
        assertEquals(1, bookingDAO.getAll().size());
    }

    @Test
    void testDeletingByReference() {
        Booking booking = new Booking(1, user, flight, passengers);
        bookingDAO.create(booking);
        bookingDAO.delete(booking);
        assertEquals(0, bookingDAO.getAll().size());
    }

    @Test
    void testDeletingById() {
        Booking booking = new Booking(1, user, flight, passengers);
        bookingDAO.create(booking);
        bookingDAO.delete(1);
        assertEquals(0, bookingDAO.getAll().size());
    }

}