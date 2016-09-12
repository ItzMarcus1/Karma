package me.itzmarcus.karma;

import me.itzmarcus.karma.Commands.*;
import me.itzmarcus.karma.KarmaTriggers.Triggers;
import me.itzmarcus.karma.MySQL.ConnectionHandler;
import me.itzmarcus.karma.MySQL.giveKarma;
import me.itzmarcus.karma.MySQL.playerRegister;
import me.itzmarcus.karma.MySQL.removeKarma;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {

    private static Plugin plugin;

    int task;

    public void onEnable() {
        saveDefaultConfig();
        plugin = this;
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new playerRegister(), this);
        pm.registerEvents(new giveKarma(), this);
        pm.registerEvents(new removeKarma(), this);
        pm.registerEvents(new ConnectionHandler(this), this);
        pm.registerEvents(new Triggers(this), this);
        getCommand("karma").setExecutor(new checkKarma(this));
        getCommand("apply").setExecutor(new Apply(this));
        getCommand("givekarma").setExecutor(new giveKarmaCommand());
        getCommand("removekarma").setExecutor(new removeKarmaCommand());
        getCommand("setkarma").setExecutor(new giveKarmaCommand());
        getCommand("staff").setExecutor(new Staff(this));
        getCommand("set-karma-for-staff").setExecutor(new setNeededForStaff(this));
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(String players : Triggers.online.keySet()) {
                    if(!(Triggers.online.get(players).intValue() == 600)) {
                        Triggers.online.put(players, Triggers.online.get(players)+1);
                    } else {
                        Triggers.online.put(players, 0);
                        giveKarma.GiveKarma(players, 5);
                        Bukkit.getPlayer(players).sendMessage("§cYou have played 10 minutes on our server (+5 karma)");
                    }
                }
            }
        }, 0, 20);
    }

    public void onDisable() {
        try{
            if(ConnectionHandler.connection != null && !ConnectionHandler.connection.isClosed()) {
                ConnectionHandler.connection.close();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
