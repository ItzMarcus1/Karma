package me.itzmarcus.karma.Commands;

import me.itzmarcus.karma.MySQL.getKarma;
import me.itzmarcus.karma.MySQL.giveKarma;
import me.itzmarcus.karma.MySQL.setKarma;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class giveKarmaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("givekarma")) {
            if(!sender.hasPermission("karma.givekarma")) {
                sender.sendMessage("§cDet har du ikke adgang til!");
                return true;
            }
            if(args.length == 0) {
                sender.sendMessage("§cBrug /givekarma <spiller> <mængde>");
            } else if(args.length == 1) {
                sender.sendMessage("§cBrug /givekarma <spiller> <mængde>");
            } else if(args.length == 2) {
                int amount = Integer.parseInt(args[1]);
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target == null) {
                    sender.sendMessage("§cDenne spiller er ikke online!");
                    return true;
                }
                sender.sendMessage("§bDu har givet " + target.getName() + " §e§l" + amount + " §bkarma point.");
                giveKarma.GiveKarma(target, amount);
                return true;
            }
        }
        if(command.getName().equalsIgnoreCase("setkarma")) {
            if(!sender.hasPermission("karma.setkarma")) {
                sender.sendMessage("§cDet har du ikke adgang til!");
                return true;
            }
            if(args.length == 0) {
                sender.sendMessage("§cBrug /setkarma <spiller> <mængde>");
            } else if(args.length == 1) {
                sender.sendMessage("§cBrug /setkarma <spiller> <mængde>");
            } else if(args.length == 2) {
                int amount = Integer.parseInt(args[1]);
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target == null) {
                    sender.sendMessage("§cDenne spiller er ikke online!");
                    return true;
                }
                sender.sendMessage("§bDu har sat " + target.getName() + "'s karma til §e§l" + amount + " §bkarma point.");
                setKarma.SetKarma(target, amount);
                return true;
            }
        }
        return false;
    }
}
