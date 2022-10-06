package booking.az.services;

import booking.az.dao.impl.BookingDAO;
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

class BookingServiceTest {
    private BookingService service;
    private User user;
    private Booking booking;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        BookingDAO bookingDAO = new BookingDAO(database);
        service = new BookingService(bookingDAO);
        user = database.getUsers().get(0);
        Flight flight = database.getFlights().get(0);
        List<Passenger> passengers =
                new ArrayList<>(List.of(new Passenger(1, "Sabina", "Ahmadova")));
        booking = new Booking(3, user, flight, passengers);
        user.addBooking(booking);
    }

    @Test
    void testGetAllBookings() {
        service.createBooking(booking);
        assertEquals(1, service.getAllBookings().size());
    }

    @Test
    void testCreatingBooking() {
        assertTrue(service.createBooking(booking));
    }

    @Test
    void testGetBookingById() {
        service.createBooking(booking);
        assertEquals(booking, service.getBooking(3));
    }

    @Test
    void testGetBookingByUser() {
        service.createBooking(booking);
        assertEquals(service.getUsersBookings(user), user.getBookings());
    }

    @Test
    void testDeletingBooking() {
        service.createBooking(booking);
        service.deleteBooking(booking, user);
        assertEquals(0, service.getAllBookings().size());
    }
}