package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.ban.BanManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_Ban implements CommandExecutor {
    public Command_Ban(AdminTools adminTools) {}
    private static final String PERM_BAN = "admintools.ban";
    private static final String PERM_ANTIBAN = "admintools.ban.priority";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_BAN) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length >= 2)
                {
                    String target = args[0];
                    String reason = "";
                    if(Bukkit.getOfflinePlayer(target).isOnline())
                    {
                        if(BanManager.isBanned(getUUID(target)))
                        {
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().AlreadyBanned);
                        }
                        else if(!Bukkit.getPlayer(target).hasPermission(PERM_ANTIBAN) && !Bukkit.getPlayer(target).hasPermission(AdminTools.getInstance().PERM_ALL))
                        {
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; i++)
                            {
                                sb.append(args[i]).append(" ");
                            }
                            reason = sb.toString().trim();
                            BanManager.ban(getUUID(target), target, commandSender.getName(), reason, -1);
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Banned.replaceAll("%player%", target).replace("%reason%", reason).replace("%enddate%", BanManager.getUnbandate(getUUID(target))));
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Antiban);
                        }
                    }
                    else
                    {
                        if(BanManager.isBanned(getUUID(target)))
                        {
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().AlreadyBanned);
                        }
                        else if(!Bukkit.getOfflinePlayer(target).isOp())
                        {
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; i++)
                            {
                                sb.append(args[i]).append(" ");
                            }
                            reason = sb.toString().trim();
                            BanManager.ban(getUUID(target), target, commandSender.getName(), reason, -1);
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Banned.replaceAll("%player%", target).replace("%reason%", reason).replace("%enddate%", BanManager.getUnbandate(getUUID(target))));
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Antiban);
                        }
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /ban <Player> <Reason>");
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cThis feature is currently disabled. Enable mysql in the config.yml file to reactivate it");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
    public String getUUID(String playername)
    {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}
