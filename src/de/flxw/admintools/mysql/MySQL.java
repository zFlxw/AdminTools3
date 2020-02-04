package de.flxw.admintools.mysql;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;
    public static Connection con;

    public static void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
            Bukkit.getConsoleSender().sendMessage(AdminTools.getInstance().Prefix + "§a MySQL Connection successfully connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        if(isConnected()) {
            try {
                con.close();
                Bukkit.getConsoleSender().sendMessage(AdminTools.getInstance().Prefix + "§a MySQL Connection successfully closed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected() {
        return con != null;
    }

    public static void createTable() {
        try {
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BannedPlayers (PLAYERNAME VARCHAR(16), UUID VARCHAR(100), ENDBAN VARCHAR(100), REASON VARCHAR(100), STAFF VARCHAR(100))");
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS MutedPlayers (PLAYERNAME VARCHAR(16), UUID VARCHAR(100), ENDMUTE VARCHAR(100), REASON VARCHAR(100), STAFF VARCHAR(100))");
            con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS PlayerInfo (PLAYERNAME VARCHAR(16), UUID VARCHAR(100), IP_ADDRESS VARCHAR(100), FIRST_JOINED VARCHAR(190), LAST_JOINED VARCHAR(190))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void update(String qry) {
        if(isConnected()) {
            try {
                con.createStatement().executeUpdate(qry);
            } catch (SQLException e) {
            }
        }
    }

    public static ResultSet getResult(String qry) {
        if(isConnected()) {
            try {
                return con.createStatement().executeQuery(qry);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public static String getCountry(InetSocketAddress ip) throws Exception
    {
        URL url = new URL("https://freegeoip.app/json/" + ip.getHostName());
        BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder entirePage = new StringBuilder();
        String inputLine;
        while ((inputLine = stream.readLine()) != null) entirePage.append(inputLine);
        stream.close();
        if(!(entirePage.toString().contains("\"country_name\":\"")))
            return null;
        return entirePage.toString().split("\"country_name\":\"")[1].split("\",")[0];
    }
}
