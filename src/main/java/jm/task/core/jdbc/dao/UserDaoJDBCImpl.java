package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try(Statement stmt = Util.connect().createStatement()) {
            stmt.executeUpdate("CREATE TABLE if not exists UsersTable(id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT, " +
                    "name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");
            System.out.println("Таблица создана.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement stmt = Util.connect().createStatement()){
            stmt.executeUpdate("DROP TABLE if exists UsersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(PreparedStatement preparedStatement = Util.connect().prepareStatement("INSERT INTO UsersTable (name, lastName, age) Values (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement preparedStatement = Util.connect().prepareStatement("DELETE FROM UsersTable WHERE Id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с номером id = " + id + " удален из базы данных.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listUsers = new ArrayList<>();
        try (PreparedStatement preparedStatement = Util.connect().prepareStatement("SELECT * FROM UsersTable")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                listUsers.add(new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getByte(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listUsers;
    }

    public void cleanUsersTable() {
        try (Statement stmt = Util.connect().createStatement()) {
            stmt.executeUpdate("DELETE FROM UsersTable");
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
