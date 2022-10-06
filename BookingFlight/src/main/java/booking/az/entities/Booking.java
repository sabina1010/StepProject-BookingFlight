package booking.az.entities;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;


public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private long id;
    private User user;
    private Flight flight;
    private List<Passenger> passengers;

    private static String bookingTime;

    public Booking(User user, Flight flight, List<Passenger> passengers) {
        this.user = user;
        this.flight = flight;
        this.passengers = passengers;
        bookingTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public Booking(long id, User user, Flight flight, List<Passenger> passengers) {
        this.id = id;
        this.user = user;
        this.flight = flight;
        this.passengers = passengers;
        bookingTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public static String getBookingTime() {
        return bookingTime;
    }

    public static void setBookingTime(String bookingTime) {
        Booking.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "BOOK ID: %d \nFLIGHT -- %s\nPASSENGERS:\n%s\nBOOK TIME: %s"
                .formatted(id, flight, getFormattedPassengers(), bookingTime);
    }

    private String getFormattedPassengers() {
        final StringBuilder[] sb = {new StringBuilder()};
        passengers.forEach(passenger ->
                sb[0] = sb[0].append("PASSENGER : FIRSTNAME: %s | LASTNAME: %s\n"
                        .formatted(passenger.getName(), passenger.getSurname())));
        return sb[0].toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return getId() == booking.getId() && Objects.equals(getUser(), booking.getUser()) && Objects.equals(getFlight(), booking.getFlight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getFlight());
    }
}
