package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Day implements CommandExecutor {
    public Command_Day(AdminTools adminTools) {
    }

    private static final String PERM_DAY = "admintools.day";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_DAY) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length == 0)
            {
                for(Player all : Bukkit.getOnlinePlayers())
                {
                    all.getWorld().setTime(7000);
                    all.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PTimeMessage.replace("%time%", "day"));
                }
                commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PublicTime.replace("%time%", "day"));
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /day");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
