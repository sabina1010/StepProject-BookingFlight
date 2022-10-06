package booking.az.utils;

import booking.az.controllers.BookingController;
import booking.az.entities.Booking;
import booking.az.entities.User;

import java.util.List;
import java.util.Scanner;

public class CancelBooking {
    private final BookingController bookingController;

    public CancelBooking(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    public void deleteBooking(User user) {
        System.out.println("\nYOUR BOOKINGS");
        List<Booking> bookings = authorizedBookings(user);
        bookings.forEach(System.out::println);

        System.out.println("\nEnter ID to cancel booking: ");
        int id = getId();
        if (id > 0 && bookings.stream().anyMatch(x -> x.getId() == id)) {
            if (bookingController.deleteBooking(bookingController.getBooking(id), user)) {
                System.out.print("\nBooking successfully deleted!\n");
            } else System.out.print("\nSomething went wrong\n");
        } else {
            System.out.print("\nID is not correct, try again!\n");
            deleteBooking(user);
        }
    }

    private List<Booking> authorizedBookings(User user) {
        return bookingController.getAllBookings()
                .stream()
                .filter(booking -> booking.getUser().getUsername().equals(user.getUsername())
                        && booking.getUser().getPassword().equals(user.getPassword()))
                .toList();
    }

    private int getId() {
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        boolean isNumber;
        do {
            if (scanner.hasNextInt()) {
                number = scanner.nextInt();
                isNumber = true;
            } else {
                System.out.print("\nIncorrect input\n");
                isNumber = false;
                scanner.next();
            }
        } while (!isNumber);

        return number;
    }
}
