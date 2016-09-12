package me.itzmarcus.karma.MySQL;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class setKarma implements Listener {

    public static void SetKarma(Player player, int amount) {
        ConnectionHandler.openConnection();
        try{
            int karmaPoints = 0;
            PreparedStatement sql = ConnectionHandler.connection.prepareStatement("SELECT karma_points FROM `player_data` WHERE player=?;");
            sql.setString(1, player.getName());

            ResultSet resultSet = sql.executeQuery();
            resultSet.next();

            karmaPoints = resultSet.getInt("karma_points");

            PreparedStatement updateKarma = ConnectionHandler.connection.prepareStatement("UPDATE `player_data` SET karma_points=? WHERE player=?;");
            updateKarma.setInt(1, amount);
            updateKarma.setString(2, player.getName());
            updateKarma.execute();

            updateKarma.close();
            sql.close();
            resultSet.close();
        }catch(Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionHandler.closeConnection();
        }
    }
}
