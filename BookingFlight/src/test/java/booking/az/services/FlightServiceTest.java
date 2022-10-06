package booking.az.services;

import booking.az.dao.impl.FlightDAO;
import booking.az.database.Database;
import booking.az.entities.Airlines;
import booking.az.entities.Cities;
import booking.az.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightServiceTest {
    private FlightService service;
    private Flight flight;
    private Flight testFlight;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        FlightDAO flightDAO = new FlightDAO(database);
        service = new FlightService(flightDAO);
        flight = database.getFlights().get(0);
        testFlight = new Flight(75, Cities.BAKU, Cities.BOSTON, Airlines.ALASKA_AIRLINES, LocalDate.now(), LocalTime.now());
    }

    @Test
    void testGetAllFlights() {
        assertEquals(20, service.getAllFlights().size());
    }

    @Test
    void testCreateDestination() {
        assertTrue(service.createDestination(testFlight));
    }

    @Test
    void testCreateDestinationFalse() {
        assertFalse(service.createDestination(flight));
    }

    @Test
    void testGetById() {
        service.createDestination(testFlight);
        assertEquals(testFlight, service.getFlight(75));
    }

    @Test
    void testDeletingFlight() {
        service.createDestination(testFlight);
        assertTrue(service.deleteFlight(75));
    }
}