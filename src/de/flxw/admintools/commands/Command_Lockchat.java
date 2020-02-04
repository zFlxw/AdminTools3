package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Lockchat implements CommandExecutor {
    public Command_Lockchat(AdminTools adminTools) {

    }

    public static boolean isChatLocked = false;
    private static final String PERM_LOCKCHAT = "admintools.lockchat";
    private static final String PERM_LC = "admintools.lc";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_LOCKCHAT) || commandSender.hasPermission(PERM_LC) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length == 0)
            {
                if(isChatLocked  == false)
                {
                    isChatLocked = true;
                    for(Player all : Bukkit.getOnlinePlayers())
                    {
                        all.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().LockchatMessage.replaceAll("%player%", commandSender.getName()));
                    }
                }
                else
                {
                    isChatLocked = false;
                    for(Player all : Bukkit.getOnlinePlayers())
                    {
                        all.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().UnlockchatMessage.replaceAll("%player%", commandSender.getName()));
                    }
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /lockchat");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }

        return true;
    }
}
