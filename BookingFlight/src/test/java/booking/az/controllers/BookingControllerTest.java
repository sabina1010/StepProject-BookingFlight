package booking.az.controllers;

import booking.az.dao.impl.BookingDAO;
import booking.az.database.Database;
import booking.az.entities.Booking;
import booking.az.entities.Flight;
import booking.az.entities.Passenger;
import booking.az.entities.User;
import booking.az.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BookingControllerTest {
    private BookingController bookingController;
    private Booking booking;
    private User user;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        BookingDAO bookingDAO = new BookingDAO(database);
        BookingService bookingService = new BookingService(bookingDAO);
        bookingController = new BookingController(bookingService);
        user = database.getUsers().get(0);
        Flight flight = database.getFlights().get(0);
        List<Passenger> passengers =
                new ArrayList<>(List.of(new Passenger(1, "Sabina", "Ahmadova")));
        booking = new Booking(3, user, flight, passengers);
        user.addBooking(booking);
    }

    @Test
    void testGetAllBookings() {
        bookingController.createBooking(booking);
        assertEquals(1, bookingController.getAllBookings().size());
    }

    @Test
    void testGetBookingById() {
        bookingController.createBooking(booking);
        assertEquals(booking, bookingController.getBooking(3));
    }

    @Test
    void testDoReservation() {
        assertTrue(bookingController.createBooking(booking));
    }

    @Test
    void testGetBookingByUser() {
        bookingController.createBooking(booking);
        assertEquals(bookingController.getAllBookings(), user.getBookings());
    }

    @Test
    void testCancelBooking() {
        bookingController.createBooking(booking);
        assertTrue(bookingController.deleteBooking(booking, user));
    }
}