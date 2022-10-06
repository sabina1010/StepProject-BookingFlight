package booking.az.utils;

public class Menus {

    public static void guestMenu(){
        String guestMenu = """
                \t\tBOOKING FLIGHT
                -> 1. Sign Up
                -> 2. Sign In
                -> 3. View Dashboard
                -> 4. Search for Flight
                -> 5. Exit
                """;
        System.out.println(guestMenu);
    }

    public static void userMenu(){
        String userMenu = """
                \t\tBOOKING FLIGHT
                -> 1. View Dashboard
                -> 2. Make Booking
                -> 3. View Bookings
                -> 4. Cancel Booking
                -> 5. Log out
                """;
        System.out.println(userMenu);
    }
}
