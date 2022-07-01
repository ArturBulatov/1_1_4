package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Tom", "Cruz", (byte) 47);
        userService.saveUser("Tom", "Cruz", (byte) 47);
        userService.saveUser("Tom", "Cruz", (byte) 47);
        userService.saveUser("John", "Snow", (byte) 41);
        userService.saveUser("Jack", "Rassel", (byte) 54);
        userService.saveUser("Megan", "Fox", (byte) 35);
        userService.removeUserById(7L);
        System.out.println(userService.getAllUsers().toString());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}
