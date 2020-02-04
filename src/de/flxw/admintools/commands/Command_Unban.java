package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.ban.BanManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_Unban implements CommandExecutor {
    public Command_Unban(AdminTools adminTools) {}

    private static final String PERM_UNBAN = "admintools.unban";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_UNBAN) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if (args.length == 1)
                {
                    String playername = args[0];
                    if (BanManager.isBanned(getUUID(playername)))
                    {
                        BanManager.unban(getUUID(playername));
                        commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().UnbanMessage.replace("%player%", playername));
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().IsNotBannedMessage);
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /unban <Player>");
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
