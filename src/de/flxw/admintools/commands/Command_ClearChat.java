package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_ClearChat implements CommandExecutor {
    public Command_ClearChat(AdminTools adminTools){}

    private static final String PERM_CLEARCHAT = "admintools.clearchat";
    private static final String PERM_CC = "admintools.cc";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_CLEARCHAT) || commandSender.hasPermission(PERM_CC) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length == 0)
            {
                for (Player all : Bukkit.getOnlinePlayers())
                {
                    for(int i = 0; i < 150; i++)
                    {
                        all.sendMessage(" ");
                    }
                    all.sendMessage(AdminTools.getInstance().ClearChatMessage.replaceAll("%player%", commandSender.getName()));
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /clearchat");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }

        return true;
    }
}
