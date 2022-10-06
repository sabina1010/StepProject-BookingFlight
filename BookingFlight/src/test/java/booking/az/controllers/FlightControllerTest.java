package booking.az.controllers;

import booking.az.dao.impl.FlightDAO;
import booking.az.database.Database;
import booking.az.entities.Airlines;
import booking.az.entities.Cities;
import booking.az.entities.Flight;
import booking.az.services.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightControllerTest {
    private FlightController flightController;
    private Flight flight;
    private Flight testFlight;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        FlightDAO flightDAO = new FlightDAO(database);
        FlightService flightService = new FlightService(flightDAO);
        flightController = new FlightController(flightService);
        flight = database.getFlights().get(0);
        testFlight = new Flight(75, Cities.BAKU, Cities.BOSTON, Airlines.ALASKA_AIRLINES, LocalDate.now(), LocalTime.now());
    }

    @Test
    void testGetAllFlights() {
        flightController.createDestination(testFlight);
        assertEquals(21, flightController.getAllFlights().size());
    }

    @Test
    void testGetFlight() {
        flightController.createDestination(testFlight);
        assertEquals(testFlight, flightController.getFlight(75));
    }

    @Test
    void testCreateDestination() {
        assertTrue(flightController.createDestination(testFlight));
    }

    @Test
    void testFalseCreateDestination() {
        assertFalse(flightController.createDestination(flight));
    }

    @Test
    void testDeletingFlight() {
        flightController.createDestination(testFlight);
        assertTrue(flightController.deleteFlight(75));
    }

}