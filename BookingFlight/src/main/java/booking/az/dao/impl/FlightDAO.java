package booking.az.dao.impl;

import booking.az.dao.DAO;
import booking.az.database.Database;
import booking.az.entities.Flight;

import java.util.List;
import java.util.Optional;

public class FlightDAO implements DAO<Flight> {

    private final Database db;

    public FlightDAO(Database db) {
        this.db = db;
    }

    @Override
    public List<Flight> getAll() {
        return db.getFlights();
    }

    @Override
    public Flight get(int id) {
        if (findById(id).isPresent()) {
            return findById(id).get();
        }
        return null;
    }

    @Override
    public boolean create(Flight flight) {
        if (findByReference(flight).isPresent()) return false;
        else db.getFlights().add(flight);
        return true;
    }

    @Override
    public boolean delete(Flight flight) {
        if (findByReference(flight).isPresent()) {
            db.getFlights().remove(flight);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        if (findById(id).isPresent()) {
            db.getFlights().remove(findById(id).get());
            return true;
        }
        return false;
    }

    private Optional<Flight> findById(int id) {
        return db.getFlights().stream()
                .filter(flight -> id == flight.getId())
                .findFirst();
    }

    private Optional<Flight> findByReference(Flight flight) {
        return db.getFlights().stream().filter(flight::equals).findFirst();
    }
}
