package me.itzmarcus.karma.MySQL;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getKarma implements Listener {

    public static int GetKarma(Player player) {
        ConnectionHandler.openConnection();
        try{
            int karmaPoints = 0;
            PreparedStatement sql = ConnectionHandler.connection.prepareStatement("SELECT karma_points FROM `player_data` WHERE player=?;");
            sql.setString(1, player.getName());

            ResultSet resultSet = sql.executeQuery();
            resultSet.next();

            karmaPoints = resultSet.getInt("karma_points");

            sql.close();
            resultSet.close();

            return karmaPoints;
        }catch(Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            ConnectionHandler.closeConnection();
        }
    }
}
