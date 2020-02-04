package de.flxw.admintools.commands;

import de.flxw.admintools.mute.MuteManager;
import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_Unmute implements CommandExecutor {
    public Command_Unmute(AdminTools adminTools) {}

    private static final String PERM_UNMUTE = "admintools.unmute";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_UNMUTE) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length == 1)
                {
                    String playername = args[0];
                    if(MuteManager.isMuted(Bukkit.getPlayer(playername).getUniqueId().toString()))
                    {
                        MuteManager.unmute(Bukkit.getPlayer(playername).getUniqueId().toString());
                        commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().UnmuteMessage.replace("%player%", playername));
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().IsNotMutedMessage);
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /unmute <Player>");
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
}
