package me.itzmarcus.karma.MySQL;

import me.itzmarcus.karma.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionHandler implements Listener {

    static Core plugin;
    public ConnectionHandler(Core instance) {
        plugin = instance;
    }


    public static Connection connection;

    private static String ip = null;
    private static String databasename = null;
    private static String username = null;
    private static String password = null;

    public synchronized static void openConnection() {
        try{
            ip = plugin.getConfig().getString("MySQL.ip");
            databasename = plugin.getConfig().getString("MySQL.database");
            username = plugin.getConfig().getString("MySQL.username");
            password = plugin.getConfig().getString("MySQL.password");
            connection = DriverManager.getConnection("jdbc:mysql://"+ip+":3306/"+databasename, username, password);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static void closeConnection() {
        try{
            connection.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static boolean playerDataContainsPlayer(Player player) {
        try{
            PreparedStatement sql = connection.prepareStatement("SELECT * FROM `player_data` WHERE player=?;");
            sql.setString(1, player.getName());
            ResultSet resultSet = sql.executeQuery();
            boolean containsPlayer = resultSet.next();

            sql.close();
            resultSet.close();

            return containsPlayer;
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
