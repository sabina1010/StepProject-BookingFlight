package booking.az.dao.impl;

import booking.az.dao.DAO;
import booking.az.database.Database;
import booking.az.entities.Booking;

import java.util.List;
import java.util.Optional;

public class BookingDAO implements DAO<Booking> {

    private final Database db;

    public BookingDAO(Database db) {
        this.db = db;
    }

    @Override
    public List<Booking> getAll() {
        return db.getBookings();
    }

    @Override
    public Booking get(int id) {
        if (findById(id).isPresent()) {
            return findById(id).get();
        }
        return null;
    }

    @Override
    public boolean create(Booking booking) {
        if (findByReference(booking).isPresent()) return false;
        db.getBookings().add(booking);
        return true;
    }

    @Override
    public boolean delete(Booking booking) {
        if (findByReference(booking).isPresent()) {
            db.getBookings().remove(booking);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (findById(id).isPresent()) {
            db.getBookings().remove(findById(id).get());
            return true;
        }
        return false;
    }

    private Optional<Booking> findById(int id) {
        return db.getBookings().stream()
                .filter(booking -> id == booking.getId())
                .findFirst();
    }

    private Optional<Booking> findByReference(Booking booking) {
        return db.getBookings().stream().filter(booking::equals).findFirst();
    }
}
