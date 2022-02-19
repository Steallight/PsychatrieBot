package de.steallight.testbot.main;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class LiteSQL {

    private static Connection conn;
    private static Statement stmt;

    public static void connect(){
        LiteSQL.conn = null;
        try {
            final File file = new File("datenbank.db");
            if(!file.exists()){
                file.createNewFile();
            }
            final String url = "jdbc:sqlite:" + file.getPath();
            LiteSQL.conn = DriverManager.getConnection(url);
            System.out.println("Verbindung wurde hergestellt!");
            LiteSQL.stmt = LiteSQL.conn.createStatement();
        }catch (final SQLException | IOException e){
            e.printStackTrace();
        }
    }
    public static void disconnect(){
        try {
            if (LiteSQL.conn != null){
                LiteSQL.conn.close();
                System.out.println("Verbindung getrennt!");
            }
        }catch (final SQLException e){
            e.printStackTrace();
        }
    }

    public static void onUpdate(final String sql){
        try {
            LiteSQL.stmt.execute(sql);
        }catch (final SQLException e){
            e.printStackTrace();
        }
    }

    public static ResultSet onQuery(final String sql){
        try {
            return LiteSQL.stmt.executeQuery(sql);
        }catch (final SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}

