package booking.az.utils;

import booking.az.controllers.UserController;
import booking.az.entities.User;

import java.util.Scanner;

public class SignIn {
    private final UserController controller;

    public SignIn(UserController controller) {
        this.controller = controller;
    }

    public User login() {
        Scanner scanner = new Scanner(System.in);
        User u = null;
        System.out.print("ENTER USERNAME: ");
        String userName = scanner.nextLine();
        System.out.print("ENTER PASSWORD: ");
        String password = scanner.nextLine();
        if (!controller.login(userName, password)) {
            System.out.print("Wrong password or username!\n");
            return login();
        } else {
            for (User user : controller.getAllUsers()) {
                u = user;
                if (u.getUsername().equals(userName) && u.getPassword().equals(password)) {
                    System.out.print("\nWELCOME " + userName.toUpperCase() + "\n");
                    return u;
                }
            }
        }
        return u;
    }
}
