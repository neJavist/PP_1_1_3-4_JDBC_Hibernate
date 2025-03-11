package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Kata1", "Kata1", (byte) 1);
        userService.saveUser("Kata2", "Kata2", (byte) 2);
        userService.saveUser("Kata3", "Kata3", (byte) 3);
        userService.saveUser("Kata4", "Kata4", (byte) 4);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
