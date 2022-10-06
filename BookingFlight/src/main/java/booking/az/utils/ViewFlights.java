package booking.az.utils;

import booking.az.controllers.FlightController;

public class ViewFlights {
    private final FlightController controller;

    public ViewFlights(FlightController controller) {
        this.controller = controller;
    }

    public void showDashboard() {
        System.out.println("\nDASHBOARD FOR ALL AVAILABLE FLIGHTS");
        controller.getAllFlights().forEach(System.out::println);
    }

}
