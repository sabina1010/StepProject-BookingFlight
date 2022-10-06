package booking.az.utils;

import booking.az.controllers.FlightController;
import booking.az.entities.Flight;

import java.util.List;
import java.util.Scanner;

public class SearchFlight {
    private final FlightController controller;

    public SearchFlight(FlightController controller) {
        this.controller = controller;
    }
    public List<Flight> searchFlight() {
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER LOCATION: ");
        String cityFrom = sc.nextLine();
        System.out.print("ENTER DESTINATION: ");
        String cityTo = sc.nextLine();
        System.out.print("Available flights : \n");
        return controller.getAvailableFlights(cityFrom, cityTo);
    }

    public void getFlight() {
        List<Flight> flights = searchFlight();
        if (flights.size() == 0) {
            System.out.print("Flight is not exist\n");
            getFlight();
        } else {
            flights.forEach(System.out::println);
        }
    }
}
