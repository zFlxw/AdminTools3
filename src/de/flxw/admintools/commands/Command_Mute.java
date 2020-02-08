package de.flxw.admintools.commands;

import de.flxw.admintools.mute.MuteManager;
import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_Mute implements CommandExecutor {
    public Command_Mute(AdminTools adminTools) {}
    private static final String PERM_MUTE = "admintools.mute";
    private static final String PERM_ANTIMUTE = "admintools.mute.priority";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_MUTE) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length >= 2)
                {
                    String target = args[0];
                    String reason = "";
                    if(Bukkit.getOfflinePlayer(target).isOnline())
                    {
                        if(MuteManager.isMuted(getUUID(target)))
                        {
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().AlreadyMuted);
                        }
                        else if(!Bukkit.getPlayer(target).hasPermission(PERM_ANTIMUTE) && !Bukkit.getPlayer(target).hasPermission(AdminTools.getInstance().PERM_ALL))
                        {
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; i++)
                            {
                                sb.append(args[i]).append(" ");
                            }
                            reason = sb.toString().trim();
                            MuteManager.mute(getUUID(target), target, commandSender.getName(), reason, -1);
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix +AdminTools.getInstance().StaffMuteMessage.replaceAll("%player%", Bukkit.getPlayer(target).getName()).replaceAll("%reason%", reason).replace("%enddate%", MuteManager.getUnbandate(getUUID(target))));
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().Antimute);
                        }
                    }
                    else
                    {
                        if(MuteManager.isMuted(getUUID(target)))
                        {
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().AlreadyMuted);
                        }
                        else if(!Bukkit.getOfflinePlayer(target).isOp())
                        {
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; i++)
                            {
                                sb.append(args[i]).append(" ");
                            }
                            reason = sb.toString().trim();
                            MuteManager.mute(getUUID(target), target, commandSender.getName(), reason, -1);
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix +AdminTools.getInstance().StaffMuteMessage.replaceAll("%player%", Bukkit.getOfflinePlayer(target).getName()).replaceAll("%reason%", reason).replace("%enddate%", MuteManager.getUnbandate(getUUID(target))));
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().Antimute);
                        }
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /mute <Player> <Reason>");
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
    public String getUUID(String playername) {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}
