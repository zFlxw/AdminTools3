package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class Command_Clearscreen implements CommandExecutor {
    public Command_Clearscreen(AdminTools adminTools) {
    }

    private static final String PERM_CLS = "admintools.cls";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_CLS) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(commandSender instanceof ConsoleCommandSender)
            {
                for(int i = 0; i < 200; i++)
                {
                    commandSender.sendMessage("");
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cThis command can only be executed by console");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
