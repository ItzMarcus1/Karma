package me.itzmarcus.karma.Commands;

import me.itzmarcus.karma.Core;
import me.itzmarcus.karma.MySQL.getKarma;
import me.itzmarcus.karma.MySQL.setKarma;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Staff implements CommandExecutor {

    Core plugin;
    public Staff(Core instance) {
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
    public double getModeratorPercent(Player player) {
        int karma = getKarma.GetKarma(player);
        double needed = plugin.getConfig().getInt("Settings.neededformod");
        double percent = karma/needed*100;
        if(percent >= 100) {
            return 100;
        } else {
            return percent;
        }
    }
    public double getAdminPercent(Player player) {
        int karma = getKarma.GetKarma(player);
        double needed = plugin.getConfig().getInt("Settings.neededforadmin");
        double percent = karma/needed*100;
        if(percent >= 100) {
            return 100;
        } else {
            return percent;
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Kun for spillere.");
            return true;
        }
        if(command.getName().equalsIgnoreCase("staff")) {
            Player p = (Player) sender;
            if(!p.hasPermission("karma.staff")) {
                p.sendMessage("§cDu skal være en §bHjælper §celler derover for at kunne bruge denne kommando!");
                return true;
            }
            if(getKarma.GetKarma(p) < 0) {
                setKarma.SetKarma(p, 0);
                double neededforhelper = plugin.getConfig().getInt("Settings.neededforstaff");
                double neededformoderator = plugin.getConfig().getInt("Settings.neededformod");
                double neededforadmin = plugin.getConfig().getInt("Settings.neededforadmin");
                p.sendMessage("§7Du har lige nu §b" + getKarma.GetKarma(p) + " §7karma point.");
                p.sendMessage("");
                p.sendMessage("§7Nedenunder kan du se hvad der kræves for de andre staff ranks:");
                p.sendMessage("§bHelper: §e" + neededforhelper + " §7(§8" + getHelperPercent(p) + "%§7)");
                p.sendMessage("§bModerator: §e" + neededformoderator + " §7(§8" + getModeratorPercent(p) + "%§7)");
                p.sendMessage("§bAdmin: §e" + neededforadmin + " §7(§8" + getAdminPercent(p) + "%§7)");
                p.sendMessage("");
                return true;
            }
            double neededforhelper = plugin.getConfig().getInt("Settings.neededforstaff");
            double neededformoderator = plugin.getConfig().getInt("Settings.neededformod");
            double neededforadmin = plugin.getConfig().getInt("Settings.neededforadmin");
                p.sendMessage("§7Du har lige nu §b" + getKarma.GetKarma(p) + " §7karma point.");
                p.sendMessage("");
                p.sendMessage("§7Nedenunder kan du se hvad der kræves for de andre staff ranks:");
                p.sendMessage("§bHelper: §e" + neededforhelper + " §7(§8" + getHelperPercent(p) + "%§7)");
                p.sendMessage("§bModerator: §e" + neededformoderator + " §7(§8" + getModeratorPercent(p) + "%§7)");
                p.sendMessage("§bAdmin: §e" + neededforadmin + " §7(§8" + getAdminPercent(p) + "%§7)");
                p.sendMessage("");
                return true;
        }
        return false;
    }
}
