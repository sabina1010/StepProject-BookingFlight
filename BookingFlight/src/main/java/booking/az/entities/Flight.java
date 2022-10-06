package booking.az.entities;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Flight implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long id;
    private static int seats;

    private Cities location;
    private Cities destination;
    private Airlines airline;
    private LocalDate date;
    private LocalTime time;

    public Flight(Cities location, Cities destination, Airlines airline, LocalDate date, LocalTime time) {
        this.location = location;
        this.destination = destination;
        this.airline = airline;
        this.date = date;
        this.time = time;
    }

    public Flight(long id, Cities location, Cities destination, Airlines airline, LocalDate date, LocalTime time) {
        this.id = id;
        this.location = location;
        this.destination = destination;
        this.airline = airline;
        this.date = date;
        this.time = time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        Flight.seats = seats;
    }

    public long getId() {
        return id;
    }

    public Cities getLocation() {
        return location;
    }

    public void setLocation(Cities location) {
        this.location = location;
    }

    public Cities getDestination() {
        return destination;
    }

    public void setDestination(Cities destination) {
        this.destination = destination;
    }

    public Airlines getAirline() {
        return airline;
    }

    public void setAirline(Airlines airline) {
        this.airline = airline;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "ID: %3d | FROM: %s ---> TO: %s | DATE: %s  TIME: %s | AIRLINE: %s"
                .formatted(id, location, destination, date, time, airline);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId() && getLocation() == flight.getLocation() && getDestination() == flight.getDestination() && getAirline() == flight.getAirline() && Objects.equals(getDate(), flight.getDate()) && Objects.equals(getTime(), flight.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLocation(), getDestination(), getAirline(), getDate(), getTime());
    }
}
