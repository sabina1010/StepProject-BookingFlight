package booking.az.utils;

import booking.az.controllers.BookingController;
import booking.az.controllers.FlightController;
import booking.az.entities.Booking;
import booking.az.entities.Flight;
import booking.az.entities.Passenger;
import booking.az.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class MakeBooking {
    private final BookingController bookingController;
    private final SearchFlight flight;
    private final FlightController flightController;

    AtomicInteger seq = new AtomicInteger();

    public MakeBooking(BookingController controllerB, SearchFlight flight, FlightController controllerF) {
        this.bookingController = controllerB;
        this.flight = flight;
        this.flightController = controllerF;
    }

    public Flight getFlight(User user) {
        List<Flight> flights = this.flight.searchFlight();
        flights.forEach(System.out::println);
        if (flights.size() > 0) {
            Flight f = selectFlight(flights, user);
            if (f == null) {
                return getFlight(user);
            } else {
                return f;
            }
        }
        return null;
    }

    public Flight selectFlight(List<Flight> flights, User user) {
        System.out.print("\nSelect flight(Enter flight ID): ");
        int id = getNumber();
        Optional<Flight> find = flights
                .stream()
                .filter(x -> x.getId() == id)
                .findFirst();
        if (id > 0 && find.isPresent()) {
            Optional<Booking> find2 = user.getBookings()
                    .stream()
                    .filter(x -> x.getFlight().getId() == id)
                    .findAny();
            if (find2.isPresent()) {
                System.out.print("\nBooking for this flight already exist!\n");
                return null;
            }
            return flightController.getFlight(id);
        } else {
            System.out.print("\nEntered ID is not exist on menu, try again!\n");
            return null;
        }
    }

    public void makeBooking(User user) {
        Flight flight = getFlight(user);
        if (flight != null) {
            Booking booking = new Booking(seq.incrementAndGet(), user, flight, addPassengers(flight));
            if (bookingController.createBooking(booking)) {
                user.addBooking(booking);
                System.out.print("\nFlight booked successfully\n");
            }
        } else {
            System.out.print("Flight is not exist\n");
            makeBooking(user);
        }
    }

    private List<Passenger> addPassengers(Flight flight) {
        List<Passenger> passengerList = new ArrayList<>();
        System.out.print("Enter seats to reserving: ");
        int seats = getNumber();
        if (seats > 0) {
            flight.setSeats(seats);
            for (int i = 1; i <= flight.getSeats(); i++) {
                Passenger p = passengerInfo(i);
                passengerList.add(p);
            }
        }
        return passengerList;
    }


    private Passenger passengerInfo(int passengerCount) {
        String fn = getName(passengerCount);
        String sn = getSurname(passengerCount);
        return new Passenger(seq.incrementAndGet(), fn, sn);
    }

    private String getName(int passengerNumber) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter name of passenger " + passengerNumber + ": ");
        return sc.nextLine();
    }

    private String getSurname(int passengerCount) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter surname of passenger " + passengerCount + ": ");
        return sc.nextLine();

    }

    private int getNumber() {
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        boolean isNumber;
        do {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                isNumber = true;
            } else {
                System.out.print("Incorrect input!\n");
                isNumber = false;
                scanner.next();
            }
        } while (!isNumber);

        return number;
    }
}
