package booking.az;

import booking.az.controllers.BookingController;
import booking.az.controllers.FlightController;
import booking.az.controllers.UserController;
import booking.az.dao.impl.BookingDAO;
import booking.az.dao.impl.FlightDAO;
import booking.az.dao.impl.UserDAO;
import booking.az.database.Database;
import booking.az.entities.Booking;
import booking.az.entities.User;
import booking.az.services.BookingService;
import booking.az.services.FlightService;
import booking.az.services.UserService;
import booking.az.utils.*;
import booking.az.utils.*;

import java.util.List;
import java.util.Scanner;

public class BookingManager {
    private final Database database = new Database();
    private final BookingController bookingController = configurationBooking(database);
    private final UserController userController = configurationUser(database);
    private final FlightController flightController = configurationFlight(database);
    private final SignIn signIn = new SignIn(userController);
    private final SignUp signUp = new SignUp(userController);
    private final SearchFlight searchFlight = new SearchFlight(flightController);
    private final ViewFlights dashboard = new ViewFlights(flightController);
    private final MakeBooking makeBooking = new MakeBooking(bookingController, searchFlight, flightController);
    private final CancelBooking deleteBooking = new CancelBooking(bookingController);

    {
        database.init();
    }

    public void guest() {

        boolean check = true;
        Scanner sc = new Scanner(System.in);
        while (check) {
            Menus.guestMenu();
            System.out.print("Enter command --> ");
            String command = sc.nextLine();
            switch (command) {
                case "1" -> signUp.register();
                case "2" -> {
                    User user = signIn.login();
                    userMenu(user);
                }
                case "3" -> dashboard.showDashboard();
                case "4" -> searchFlight.getFlight();
                case "5" -> {
                    database.close();
                    check = false;
                }
                default -> System.out.println("Incorrect command!");
            }
        }

    }

    private void userMenu(User user) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        while (check) {
            Menus.userMenu();
            System.out.print("Enter command --> ");
            String command = sc.nextLine();
            switch (command) {
                case "1" -> dashboard.showDashboard();
                case "2" -> makeBooking.makeBooking(user);
                case "3" -> {
                    List<Booking> bookings = authorizedBookings(user);
                    if (bookings.size() > 0) {
                        bookings.forEach(System.out::println);
                    } else System.out.print("\nYou haven't bookings\n");
                }
                case "4" -> {
                    List<Booking> bookings = authorizedBookings(user);
                    if (bookings.size() > 0) {
                        deleteBooking.deleteBooking(user);
                    } else System.out.print("\nNo any booking for cancelling\n");
                }
                case "5" -> {
                    System.out.println("Logged out..");
                    database.close();
                    check = false;
                }
                default -> System.out.println("Incorrect command");
            }
        }

    }


    private List<Booking> authorizedBookings(User user) {
        return bookingController.getAllBookings()
                .stream()
                .filter(x -> x.getUser().getUsername().equals(user.getUsername())
                        && x.getUser().getPassword().equals(user.getPassword()))
                .toList();
    }

    private UserController configurationUser(Database db) {
        UserDAO userDAO = new UserDAO(db);
        UserService userService = new UserService(userDAO);
        return new UserController(userService);
    }

    private FlightController configurationFlight(Database db) {
        FlightDAO flightDAO = new FlightDAO(db);
        FlightService flightService = new FlightService(flightDAO);
        return new FlightController(flightService);
    }

    private BookingController configurationBooking(Database db) {
        BookingDAO bookingDAO = new BookingDAO(db);
        BookingService bookingService = new BookingService(bookingDAO);
        return new BookingController(bookingService);
    }
}
