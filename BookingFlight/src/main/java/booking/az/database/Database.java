package booking.az.database;

import booking.az.entities.Booking;
import booking.az.entities.Flight;
import booking.az.entities.User;
import booking.az.utils.RandomFlightGenerator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private final File userBase = new File("userBase.bin");
    private final File bookingBase = new File("bookingBase.bin");
    private final File flightBase = new File("flightBase.bin");

    private final List<User> users = new ArrayList<>();
    private final List<Booking> bookings = new ArrayList<>();
    private final List<Flight> flights = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    private void saveToBase(File file, List list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    private void loadFromBase(File file, List list) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            list.addAll((List) in.readObject());
        } catch (IOException | ClassNotFoundException cs) {
            cs.printStackTrace();
        }
    }

    public void init() {
        RandomFlightGenerator randomGenerator = new RandomFlightGenerator();
        if (flightBase.exists()) {
            loadFromBase(flightBase, flights);
        } else {
            flights.addAll(randomGenerator.randomFlights(20));
            saveToBase(flightBase, flights);
        }
        if (userBase.exists()) {
            loadFromBase(userBase, users);
        }
        if (bookingBase.exists()) {
            loadFromBase(bookingBase, bookings);
        }
    }

    public void close() {
        saveToBase(flightBase, flights);
        saveToBase(userBase, users);
        saveToBase(bookingBase, bookings);
    }

}
