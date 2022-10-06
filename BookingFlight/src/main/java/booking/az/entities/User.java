package booking.az.entities;


import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long id;
    private String username;
    private String password;
    private static List<Booking> bookings = new ArrayList<>();

    public User() {
        bookings = new ArrayList<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    @Override
    public String toString() {
        return "User{id=%d, username='%s', password='%s'}".formatted(id, username, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() && getUsername().equals(user.getUsername()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword());
    }

    public void addBooking(Booking booking) {
        if (findByReference(booking).isPresent()) {
            System.out.println("This booking already created, check your bookings list please");
        } else {
            bookings.add(booking);
        }
    }

    private Optional<Booking> findByReference(Booking b) {
        if (b == null) return Optional.empty();
        return bookings.stream()
                .filter(x -> x.equals(b))
                .findFirst();
    }
}
