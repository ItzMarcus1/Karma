package me.itzmarcus.karma.Commands;

import me.itzmarcus.karma.Core;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class setNeededForStaff implements CommandExecutor {

    Core plugin;
    public setNeededForStaff(Core instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(command.getName().equalsIgnoreCase("set-karma-for-staff")) {
            if(!sender.hasPermission("karma.setrequirement")) {
                sender.sendMessage("§cDet har du ikke adgang til.");
                return true;
            }
            if(args.length == 0) {
                sender.sendMessage("§aDu kan gøre det sådan her: §c/set-karma-for-staff <antal>");
            } else if(args.length == 1) {
                int newAmount = Integer.parseInt(args[0]);
                plugin.getConfig().set("Settings.neededforstaff", newAmount);
            }
        }
        return false;
    }
}
