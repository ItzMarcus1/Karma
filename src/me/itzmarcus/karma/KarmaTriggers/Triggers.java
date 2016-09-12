package me.itzmarcus.karma.KarmaTriggers;

import me.itzmarcus.karma.Core;
import me.itzmarcus.karma.MySQL.giveKarma;
import me.itzmarcus.karma.Packets.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Triggers implements Listener {

    Core plugin;
    public Triggers(Core instance) {
        plugin = instance;
    }

    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<String> usedVelkommen = new ArrayList<String>();
    public static HashMap<String, Integer> online = new HashMap<String, Integer>();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if(!p.hasPlayedBefore()) {
            usedVelkommen.clear();
            names.add(p.getName());
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    names.remove(p.getName());
                }
            }, 20 * 30);
        }
    }

    @EventHandler
    public void onVelkommen(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String message = e.getMessage();
        for(String newPlayer : names) {
            if(message.contains("Welcome")) {
                if(!usedVelkommen.contains(p.getName())) {
                    usedVelkommen.add(p.getName());
                    p.sendMessage("§cThanks for helping us, by saying 'Welcome' to the new players! (+10 Karma)");
                    giveKarma.GiveKarma(p, 10);
                } else {
                    return;
                }
            }
        }
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        online.put(p.getName(), 0);
    }
    @EventHandler
    public void leave(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        online.remove(p.getName());
    }

}
