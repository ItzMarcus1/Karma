package me.itzmarcus.karma.Commands;

import me.itzmarcus.karma.Core;
import me.itzmarcus.karma.MySQL.ConnectionHandler;
import me.itzmarcus.karma.MySQL.getKarma;
import me.itzmarcus.karma.MySQL.setKarma;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class checkKarma implements CommandExecutor {

    Core plugin;
    public checkKarma(Core instance) {
        plugin = instance;
    }

    public double getHelperPercent(Player player) {
        int karma = getKarma.GetKarma(player);
        double needed = plugin.getConfig().getInt("Settings.neededforstaff");
        double percent = karma/needed*100;
        if(percent >= 100) {
            return 100;
        } else {
            return percent;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("karma")) {
            if(args.length == 0) {
                sender.sendMessage("§cBrug /karma <spiller>");
            } else if(args.length == 1) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target == null) {
                    sender.sendMessage("§cDenne spiller er ikke online!");
                    return true;
                }
                if(getKarma.GetKarma(target) < 0) {
                    setKarma.SetKarma(target, 0);
                    int karma = getKarma.GetKarma(target);
                    double needed = plugin.getConfig().getInt("Settings.neededforstaff");
                    double percent = karma/needed*100;
                    sender.sendMessage("§7Karma Stats for §e" + target.getName() + "§7:");
                    sender.sendMessage("§bKarma Point: §e" + getKarma.GetKarma(target));
                    sender.sendMessage("§bHjælper Mulighed: §e"+getKarma.GetKarma(target)+"§b/§e"+plugin.getConfig().getInt("Settings.neededforstaff") + " §7(§8" + getHelperPercent(target) + "%§7)");
                    return true;
                }
                    int karma = getKarma.GetKarma(target);
                    double needed = plugin.getConfig().getInt("Settings.neededforstaff");
                    double percent = karma/needed*100;
                    sender.sendMessage("§7Karma Stats for §e" + target.getName() + "§7:");
                    sender.sendMessage("§bKarma Point: §e" + getKarma.GetKarma(target));
                    sender.sendMessage("§bHjælper Mulighed: §e"+getKarma.GetKarma(target)+"§b/§e"+plugin.getConfig().getInt("Settings.neededforstaff") + " §7(§8" + getHelperPercent(target) + "%§7)");
                    return true;
            }
        }
        return false;
    }
}
