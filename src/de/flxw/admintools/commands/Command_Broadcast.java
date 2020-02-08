package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_Broadcast implements CommandExecutor {
    public Command_Broadcast(AdminTools adminTools) {}
    private static final String PERM_BROADCAST = "admintools.broadcast";
    private static final String PERM_BROADCAST_COLOR = "admintools.broadcast.color";
    private static final String PERM_BC = "admintools.bc";
    private static final String PERM_BC_COLOR = "admintools.bc.color";
    private static final String PERM_BC_ALL = "admintools.bc.*";
    private static final String PERM_BROADCAST_ALL = "admintools.bc.*";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_BROADCAST) || commandSender.hasPermission(PERM_BC) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length >= 1)
            {
                String message;
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < args.length; i++)
                {
                    sb.append(args[i]).append(" ");
                }
                message = sb.toString().trim();
                if(commandSender.hasPermission(PERM_BROADCAST_COLOR) || commandSender.hasPermission(PERM_BC_COLOR) || commandSender.hasPermission(PERM_BROADCAST_ALL) || commandSender.hasPermission(PERM_BC_ALL) ||commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    Bukkit.getServer().broadcastMessage(AdminTools.getInstance().BroadcastPrefix + ChatColor.translateAlternateColorCodes('&', message));
                }
                else
                {
                    Bukkit.getServer().broadcastMessage(AdminTools.getInstance().BroadcastPrefix + message);
                    if(message.contains("&"))
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BroadcastWarning);
                    }
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /broadcast <Message>");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
