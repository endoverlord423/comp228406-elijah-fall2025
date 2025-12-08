package com.comp228.lab5;

import com.comp228.lab5.records.PlayerRecord;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSQLManager {
    private final String connectionURL;
    private final String playerTable = "player";
    private final String gameTable = "game";

    public PostgreSQLManager(String url, int port, String dbName, String user, String password) {
        this.connectionURL = "jdbc:postgresql://" + url + ":" + port + "/" + dbName + "?user=" + user + "&password=" + password;
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(connectionURL);
    }

    public boolean addPlayerRecord(String firstName, String lastName, String address, String postalCode, String province, int phoneNumber) {
        String query = "INSERT INTO " + playerTable + " (first_name, last_name, address, postal_code, province, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.setString(4, postalCode);
            stmt.setString(5, province);
            stmt.setInt(6, phoneNumber);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addGameRecord(String gameTitle) {
        String query = "INSERT INTO " + gameTable + " (game_title) VALUES (?)";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, gameTitle);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<PlayerRecord> getAllPlayerRecords() {
        String query = "SELECT * FROM " + playerTable;
        List<PlayerRecord> records = new ArrayList<>();
        try (Connection conn = connect(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                records.add(new PlayerRecord(rs.getString("first_name"), rs.getString("last_name"), rs.getString("address"), rs.getString("postal_code"), rs.getString("province"), rs.getInt("phone_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    public boolean deletePlayerRecord(String firstName, String lastName) {
        String query = "DELETE FROM " + playerTable + " WHERE first_name=? AND last_name=?";
        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int countPlayerRecords() {
        String query  = "SELECT COUNT(*) FROM " + playerTable;
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
