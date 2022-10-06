package booking.az.utils;

import booking.az.controllers.UserController;
import booking.az.entities.User;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class SignUp {
    private final UserController controller;
    AtomicInteger seq = new AtomicInteger();

    public SignUp(UserController controller) {
        this.controller = controller;
    }

    public void register() {
        if (controller.register(getNewUser())) {
            System.out.print("\nRegistration completed\n");
        } else System.out.print("\nRegistration failed!\n");
    }

    private User getNewUser() {
        String userName = getUserName();
        String password = getPassword();
        return new User(seq.incrementAndGet(), userName, password);
    }

    private String getUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username (at least 4 character): ");
        String userName = scanner.nextLine();
        if (userName.length() > 3) {
            return userName;
        } else return getUserName();
    }

    private String getPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password (at least 4 character): ");
        String password = scanner.nextLine();
        if (password.length() > 3) {
            return password;
        } else return getPassword();
    }

}
