package booking.az.dao.impl;

import booking.az.database.Database;
import booking.az.entities.Airlines;
import booking.az.entities.Cities;
import booking.az.entities.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightDAOTest {
    private Flight flight;
    private Flight testFlight;
    private FlightDAO flightDAO;

    @BeforeEach
    void setUp() {
        Database database = new Database();
        database.init();
        flightDAO = new FlightDAO(database);
        flight = database.getFlights().get(0);
        testFlight = new Flight(75, Cities.BAKU, Cities.BOSTON, Airlines.ALASKA_AIRLINES, LocalDate.now(), LocalTime.now());
    }

    @Test
    void testGetAll() {
        assertEquals(20, flightDAO.getAll().size());
    }

    @Test
    void testGetById() {
        assertEquals(flight, flightDAO.get((int) flight.getId()));
    }

    @Test
    void testCreate() {
        flightDAO.create(testFlight);
        assertEquals(21, flightDAO.getAll().size());
    }

    @Test
    void testDeletingByReference() {
        flightDAO.create(testFlight);
        flightDAO.delete(testFlight);
        assertEquals(20, flightDAO.getAll().size());
    }

    @Test
    void testDeletingById() {
        flightDAO.create(testFlight);
        flightDAO.delete(75);
        assertEquals(20, flightDAO.getAll().size());
    }

}