package booking.az.services;

import booking.az.dao.impl.BookingDAO;
import booking.az.entities.Booking;
import booking.az.entities.User;

import java.util.List;
import java.util.Optional;

public class BookingService {
    private final BookingDAO dao;

    public BookingService(BookingDAO dao) {
        this.dao = dao;
    }

    public List<Booking> getAllBookings() {
        return dao.getAll();
    }

    public Booking getBooking(int id) {
        return dao.get(id);
    }

    public List<Booking> getUsersBookings(User user) {
        return dao.getAll()
                .stream()
                .filter(booking -> booking.getUser().equals(user))
                .toList();
    }


    public boolean createBooking(Booking booking) {
        return dao.create(booking);
    }

    public boolean deleteBooking(Booking booking, User user) {
        Optional<Booking> find = getUsersBookings(user)
                .stream()
                .filter(x -> x.equals(booking))
                .findFirst();

        if (find.isPresent()) {
            user.getBookings().remove(booking);
        }
        return dao.delete(booking);
    }
}
