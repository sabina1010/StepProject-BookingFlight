package booking.az.entities;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Passenger implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private long id;
    private String name;
    private String surname;

    public Passenger(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Passenger(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "PASSENGER --> FIRSTNAME: %s  LASTNAME: %s\n".formatted(name, surname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return getId() == passenger.getId() && Objects.equals(getName(), passenger.getName()) && Objects.equals(getSurname(), passenger.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname());
    }
}
