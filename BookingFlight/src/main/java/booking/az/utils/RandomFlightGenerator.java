package booking.az.utils;

import booking.az.entities.Airlines;
import booking.az.entities.Cities;
import booking.az.entities.Flight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RandomFlightGenerator {
    private final Random random = new Random();
    AtomicInteger seq = new AtomicInteger();

    private Airlines randomAirline() {
        return Airlines.values()[random.nextInt(Airlines.values().length)];
    }

    private Cities randomCity() {
        return Cities.values()[random.nextInt(Cities.values().length)];
    }


    private LocalDate randomDate() {
        return LocalDate.now().plusDays(random.nextInt(30));
    }

    private LocalTime randomTime() {
        int randomHour = random.nextInt(23) + 1;
        int randomMinute = random.nextInt(4) * 10;
        if (randomHour < 10) {
            randomHour = Integer.parseInt("0" + randomHour);
        }
        return LocalTime.of(randomHour, randomMinute);
    }

    private Flight generate() {
        Airlines airline = randomAirline();
        Cities location = randomCity();
        Cities destination = randomCity();
        LocalDate date = randomDate();
        LocalTime time = randomTime();
        return new Flight(seq.incrementAndGet(), location, destination, airline, date, time);
    }

    public List<Flight> randomFlights(int count) {
        Set<Flight> flights = new HashSet<>();
        Flight flight;
        for (int i = 0; i < count; i++) {
            flight = generate();
            flights.add(flight);
        }
        return new ArrayList<>(flights);
    }

}
