package booking.az.controllers;

import booking.az.entities.Flight;
import booking.az.services.FlightService;

import java.util.List;

public class FlightController {
    private final FlightService service;

    public FlightController(FlightService service) {
        this.service = service;
    }

    public List<Flight> getAllFlights() {
        return service.getAllFlights();
    }

    public Flight getFlight(int id) {
        return service.getFlight(id);
    }

    public boolean createDestination(Flight flight) {
        return service.createDestination(flight);
    }

    public List<Flight> getAvailableFlights(String location, String destination) {
        return service.getAvailableFlights(location, destination);
    }

    public boolean deleteFlight(int id) {
        return service.deleteFlight(id);
    }
}
