package me.itzmarcus.karma.Commands;

import me.itzmarcus.karma.MySQL.getKarma;
import me.itzmarcus.karma.MySQL.giveKarma;
import me.itzmarcus.karma.MySQL.removeKarma;
import me.itzmarcus.karma.MySQL.setKarma;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class removeKarmaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("removekarma")) {
            if(!sender.hasPermission("karma.removekarma")) {
                sender.sendMessage("§cDet har du ikke adgang til!");
                return true;
            }
            if(args.length == 0) {
                sender.sendMessage("§cBrug /removekarma <spiller> <mængde>");
            } else if(args.length == 1) {
                sender.sendMessage("§cBrug /removekarma <spiller> <mængde>");
            } else if(args.length == 2) {
                int amount = Integer.parseInt(args[1]);
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if(target == null) {
                    sender.sendMessage("§cDenne spiller er ikke online!");
                    return true;
                }
                sender.sendMessage("§bDu har taget §e" + amount + " §bkarma point fra §e" + target.getName() + "§b.");
                removeKarma.RemoveKarma(target, amount);
                return true;
            }
        }
        return false;
    }
}
