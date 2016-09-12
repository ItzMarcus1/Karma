package me.itzmarcus.karma.Commands;

import me.itzmarcus.karma.Core;
import me.itzmarcus.karma.MySQL.getKarma;
import me.itzmarcus.karma.MySQL.setKarma;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Apply implements CommandExecutor {

    Core plugin;
    public Apply(Core instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Kun for spillere.");
            return true;
        }
        if(command.getName().equalsIgnoreCase("apply")) {
            Player p = (Player) sender;
            if(getKarma.GetKarma(p) < 0) {
                setKarma.SetKarma(p, 0);
                int karma = getKarma.GetKarma(p);
                int displayneeded = plugin.getConfig().getInt("Settings.neededforstaff");
                double needed = plugin.getConfig().getInt("Settings.neededforstaff");
                double percent = karma/needed*100;
                p.sendMessage("");
                p.sendMessage("§cDu kan desværre ikke ansøge. Vi kræver at man har over §b" + displayneeded + " karma point!");
                p.sendMessage("§cDu kan få §bkarma §cved fx. at skrive §b'Velkommen' §ctil nye spillere der joiner (Inden 30 sekunder)");
                p.sendMessage("");
                p.sendMessage("§bDu har §e" + getKarma.GetKarma(p) + "§b/§e" + displayneeded + " §bkarma point. §7(§8" + percent + "%§7)");
                p.sendMessage("");
                return true;
            }else if(getKarma.GetKarma(p) < plugin.getConfig().getInt("Settings.neededforstaff")) {
                    int karma = getKarma.GetKarma(p);
                    int displayneeded = plugin.getConfig().getInt("Settings.neededforstaff");
                    double needed = plugin.getConfig().getInt("Settings.neededforstaff");
                    double percent = karma/needed*100;
                    p.sendMessage("");
                    p.sendMessage("§cDu kan desværre ikke ansøge. Vi kræver at man har over §b" + displayneeded + " karma point!");
                    p.sendMessage("§cDu kan få §bkarma §cved fx. at skrive §b'Velkommen' §ctil nye spillere der joiner (Inden 30 sekunder)");
                    p.sendMessage("");
                    p.sendMessage("§bDu har §e" + getKarma.GetKarma(p) + "§b/§e" + displayneeded + " §bkarma point. §7(§8" + percent + "%§7)");
                    p.sendMessage("");
                    return true;
                } else {
                    p.sendMessage("§bDu kan ansøge på §ewww.darkstarpvp.com/ansøg");
                    return true;
                }
        }
        return false;
    }
}
