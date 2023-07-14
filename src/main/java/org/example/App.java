package org.example;


import java.sql.*;

public class App {

    private final String url = "jdbc:postgresql://localhost:54320/postgres";
    private final String user = "user";
    private final String password = "admin";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        app.getSongs();
    }

    public void getSongs() {

        String SQL = "SELECT id,artist, name FROM song";

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            displaySong(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displaySong(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("id") + "\t"
                    + rs.getString("artist") + "\t"
                    + rs.getString("name"));

        }
    }
}
