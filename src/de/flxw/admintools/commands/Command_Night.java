package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Night implements CommandExecutor {
    public Command_Night(AdminTools adminTools) {}

    private static final String PERM_NIGHT = "admintools.night";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_NIGHT) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length == 0)
            {
                for(Player all : Bukkit.getOnlinePlayers())
                {
                    all.getWorld().setTime(15000);
                    all.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PTimeMessage.replace("%time%", "night"));
                }
                commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PublicTime.replace("%time%", "night"));
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /night");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
