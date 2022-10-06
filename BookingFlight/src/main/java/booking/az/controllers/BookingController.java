package booking.az.controllers;

import booking.az.entities.Booking;
import booking.az.entities.User;
import booking.az.services.BookingService;

import java.util.List;

public class BookingController {
    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    public List<Booking> getAllBookings() {
        return service.getAllBookings();
    }

    public Booking getBooking(int id) {
        return service.getBooking(id);
    }

    public List<Booking> getUsersBookings(User user) {
        return service.getUsersBookings(user);
    }

    public boolean createBooking(Booking booking) {
        return service.createBooking(booking);
    }

    public boolean deleteBooking(Booking booking, User user) {
        return service.deleteBooking(booking, user);
    }
}
