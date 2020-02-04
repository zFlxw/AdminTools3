package de.flxw.admintools.commands;

import de.flxw.admintools.mysql.MySQL;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.PlayerInfoManager;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;

public class Command_Playerinfo implements CommandExecutor {
    public Command_Playerinfo(AdminTools adminTools) {
    }

    private static final String PERM_PLAYERINFO = "admintools.playerinfo";
    private static final String PERM_PLAYERINFO_IP = "admintools.playerinfo.ip";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_PLAYERINFO) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length == 1)
            {
                Player target = Bukkit.getPlayer(args[0]);
                OfflinePlayer offlineTarget = Bukkit.getOfflinePlayer(args[0]);
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7§m---------------[ §c§lPlayerinfo §7§m]---------------");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7Playername: §a" + offlineTarget.getName());
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7UUID: §a" + offlineTarget.getUniqueId().toString());
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7Status: " + (offlineTarget.isOnline() ? "§aOnline" : "§cOffline"));
                try {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7Country (IP based): §a" + MySQL.getCountry(new InetSocketAddress(target.getAddress().getAddress().getHostAddress(), 0)));
                } catch (Exception e) {
                }
                if(commandSender.hasPermission(PERM_PLAYERINFO_IP) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    if(offlineTarget.isOnline())
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7IP: §a" + target.getAddress().getAddress().getHostAddress());
                    }
                    else if(!offlineTarget.isOnline() && PlayerInfoManager.isPlayerInTable(offlineTarget.getUniqueId().toString()) && AdminTools.getInstance().LogIP)
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7IP: §a" + PlayerInfoManager.getPlayerIp(offlineTarget.getUniqueId().toString()));
                    }
                    else if(!offlineTarget.isOnline() && !PlayerInfoManager.isPlayerInTable(offlineTarget.getUniqueId().toString()))
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7IP: §c<none> (This player wasn't online yet)");
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7IP: §c" + PlayerInfoManager.getPlayerIp(offlineTarget.getUniqueId().toString()));
                    }
                }
                else
                {

                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7IP: §a" + target.getAddress().getAddress().getHostAddress().replaceAll("[123456789]", "*"));
                }
                if(AdminTools.getInstance().MySQLcon)
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7First joined: §a" + PlayerInfoManager.getPlayerFirstJoinDate(offlineTarget.getUniqueId().toString()));
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7Last joined: §a" + PlayerInfoManager.getPlayerLastJoinDate(offlineTarget.getUniqueId().toString()));
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7First joined: §cEnable MySQL to see this information" );
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7Last joined: §cEnable MySQL to see this information");
                }
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7§m---------------[ §c§lPlayerinfo §7§m]---------------");
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /playerinfo <player>");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
