package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Adminchat implements CommandExecutor {
    public Command_Adminchat(AdminTools adminTools) {
    }
    private static final String PERM_ADMINCHAT = "admintools.adminchat";
    private static final String PERM_AC = "admintools.ac";
    private static final String PERM_ADMINCHAT_NOTFIY = "admintools.adminchat.notify";
    private static final String PERM_AC_NOTFIY = "admintools.ac.notify";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(player.hasPermission(PERM_ADMINCHAT) || player.hasPermission(PERM_AC) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(player.hasPermission(PERM_ADMINCHAT_NOTFIY) || player.hasPermission(PERM_AC_NOTFIY) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    if(args.length == 1)
                    {
                        if(args[0].equalsIgnoreCase("on"))
                        {
                            if(!ArrayLists.adminchatNotify.contains(player))
                            {
                                ArrayLists.adminchatNotify.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdminchatLogIn);
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdminchatAlreadyLogIn);
                            }
                        }
                        else if(args[0].equalsIgnoreCase("off"))
                        {
                            if(ArrayLists.adminchatNotify.contains(player))
                            {
                                ArrayLists.adminchatNotify.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdminchatLogOff);
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdminchatAlreadyLogOff);
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /adminchat <on/off>");
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /adminchat <on/off>");
                    }
                }
                else
                {
                    if(args.length >= 1)
                    {
                        String message = "";
                        for(int i = 0; i < args.length; i++)
                        {
                            message+=args[i] + " ";
                        }
                        for(Player notify : ArrayLists.adminchatNotify)
                        {
                            notify.sendMessage(AdminTools.getInstance().Prefix + "§7§m---------------[§c§l Adminchat §7§m]---------------");
                            notify.sendMessage(AdminTools.getInstance().Prefix + " ");
                            notify.sendMessage(AdminTools.getInstance().Prefix + "§7Player: §a" + player.getName());
                            notify.sendMessage(AdminTools.getInstance().Prefix + "§7Message: §a" + message);
                            notify.sendMessage(AdminTools.getInstance().Prefix + " ");
                            notify.sendMessage(AdminTools.getInstance().Prefix + "§7§m---------------[§c§l Adminchat §7§m]---------------");
                        }
                        player.sendMessage(AdminTools.getInstance().AdminchatMessage.replace("%message%", message));
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /adminchat <message>");
                    }
                }
            }
            else
            {
                player.sendMessage(AdminTools.getInstance().NoPerm);
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPlayer);
        }
        return true;
    }
}
