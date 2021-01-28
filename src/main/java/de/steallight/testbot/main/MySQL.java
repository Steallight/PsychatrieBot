package de.steallight.testbot.main;

import java.sql.*;

public class MySQL {
    public static String host = "176.9.181.228";
    public static String port = "3306";
    public static String database = "Test";
    public static String username = "newBot";
    public static String password = "F^h8f2e2";
    public static Connection con;


    public static void connect() {
        if (!isConnected()) {
            try {

                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                System.out.println("[MySQL] Verbindung hergestellt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public static void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                System.out.println("[MySQL] Verbindung geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean isConnected() {
        return (con != null);
    }

    public static void update(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTable() {
        if (isConnected()) {
            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS TeamMembers (Username VARCHAR(100), MemberID VARCHAR(100), Rolle VARCHAR(100), Beitritt VARCHAR(100)");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public static ResultSet getResult(String qry) {
        try {
            PreparedStatement ps = con.prepareStatement(qry);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
