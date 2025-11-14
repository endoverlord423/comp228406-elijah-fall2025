package com.example.hellofx;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLManager {
    private final String connectionURL;
    private final String tableName = "comp228_demo";

    public PostgreSQLManager(String url, int port, String dbName, String user, String password) {
        this.connectionURL = "jdbc:postgresql://" + url + ":" + port + "/" + dbName + "?user=" + user + "&password=" + password;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(connectionURL);
    }

    public boolean addRecord(String firstName, String lastName, int age, String favoriteColor, String collegeProgram) {
        String query = "INSERT INTO " + tableName + " (first_name, last_name, age, favorite_color, college_program) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setString(4, favoriteColor);
            stmt.setString(5, collegeProgram);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<StudentRecord> getAllRecords() {
        String query = "SELECT * FROM " + tableName;
        List<StudentRecord> records = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                records.add(new StudentRecord(rs.getString("first_name"), rs.getString("last_name"), rs.getInt("age"), rs.getString("favorite_color"), rs.getString("college_program")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public boolean updateRecord(String oldFirstName, String newFirstName, String lastName, int age, String color, String program) {
        String query = "UPDATE " + tableName + " SET first_name=?, last_name=?, age=?, favorite_color=?, college_program=? WHERE first_name=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, newFirstName);
            stmt.setString(2, lastName);
            stmt.setInt(3, age);
            stmt.setString(4, color);
            stmt.setString(5, program);
            stmt.setString(6, oldFirstName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecord(String firstName, String lastName) {
        String query = "DELETE FROM " + tableName + " WHERE first_name=? AND last_name=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countRecords() {
        String query  = "SELECT COUNT(*) FROM " + tableName;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
