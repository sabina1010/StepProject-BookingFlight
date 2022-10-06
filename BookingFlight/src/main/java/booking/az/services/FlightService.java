package booking.az.services;

import booking.az.dao.impl.FlightDAO;
import booking.az.entities.Flight;

import java.util.List;

public class FlightService {
    private final FlightDAO dao;

    public FlightService(FlightDAO dao) {
        this.dao = dao;
    }

    public List<Flight> getAllFlights() {
        return dao.getAll();
    }

    public Flight getFlight(int id) {
        return dao.get(id);
    }

    public boolean createDestination(Flight flight) {
        return dao.create(flight);
    }

    public List<Flight> getAvailableFlights(String location, String destination) {
        return dao.getAll().stream()
                .filter(flight -> flight.getLocation().name().equalsIgnoreCase(location)
                        && flight.getDestination().name().equalsIgnoreCase(destination))
                .toList();
    }

    public boolean deleteFlight(int id) {
        return dao.delete(id);
    }
}
