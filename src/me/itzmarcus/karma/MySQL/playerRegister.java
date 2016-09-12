package me.itzmarcus.karma.MySQL;

import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class playerRegister implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        ConnectionHandler.openConnection();
        if(!ConnectionHandler.playerDataContainsPlayer(p)) {
            try {
                PreparedStatement sql = ConnectionHandler.connection.prepareStatement("INSERT INTO `player_data` VALUES(?,0);");
                sql.setString(1, p.getName());
                sql.execute();
                sql.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
