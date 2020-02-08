package de.flxw.admintools.commands;

import de.flxw.admintools.mute.MuteManager;
import de.flxw.admintools.mute.MuteUnit;
import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Command_Tempmute implements CommandExecutor {
    public Command_Tempmute(AdminTools adminTools) {}
    private static final String PERM_TEMPMUTE = "admintools.tempmute";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_TEMPMUTE) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length >= 4)
                {
                    String playername = args[0];
                    long value = 0;
                    if(!MuteManager.isMuted(getUUID(playername)))
                    {
                        if(Bukkit.getOfflinePlayer(playername).isOnline())
                        {
                            if(!Bukkit.getPlayer(playername).hasPermission(PERM_TEMPMUTE) || !Bukkit.getPlayer(playername).hasPermission(AdminTools.getInstance().PERM_ALL))
                            {
                                try
                                {
                                    value = Integer.valueOf(args[1]);
                                }
                                catch(NumberFormatException ex)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tempmute <Player> <Number> <Unit> <Reason>");
                                }
                                if(value >= 500)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cPlease choose a Number under 500!");
                                }

                                String unitString = args[2];
                                String reason = "";

                                StringBuilder sb = new StringBuilder();
                                for(int i = 3; i < args.length; i++)
                                {
                                    sb.append(args[i]).append(" ");
                                }
                                reason = sb.toString().trim();
                                List<String> unitList = MuteUnit.getUnitsAsString();
                                if(unitList.contains(unitString.toLowerCase()))
                                {
                                    MuteUnit unit = MuteUnit.getUnit(unitString);
                                    long seconds = value * unit.getToSecond();
                                    MuteManager.mute(getUUID(playername), playername, commandSender.getName(), reason, seconds);
                                    commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().StaffMuteMessage.replace("%player%", playername).replace("%reason%", reason).replace("%enddate%", "until " + MuteManager.getUnbandate(getUUID(playername))));
                                }
                                else
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cYou can only use the following units: §4s (seconds), m (minutes), h (hours), d (days), w (weeks)");
                                }
                            }
                            else
                            {
                                commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().Antimute);
                            }
                        }
                        else
                        {
                            if(!Bukkit.getOfflinePlayer(playername).isOp())
                            {
                                try
                                {
                                    value = Integer.valueOf(args[1]);
                                }
                                catch(NumberFormatException ex)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tempmute <Player> <Number> <Unit> <Reason>");
                                }
                                if(value >= 500)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cPlease choose a Number under 500!");
                                }

                                String unitString = args[2];
                                String reason = "";

                                StringBuilder sb = new StringBuilder();
                                for(int i = 3; i < args.length; i++)
                                {
                                    sb.append(args[i]).append(" ");
                                }
                                reason = sb.toString().trim();
                                List<String> unitList = MuteUnit.getUnitsAsString();
                                if(unitList.contains(unitString.toLowerCase()))
                                {
                                    MuteUnit unit = MuteUnit.getUnit(unitString);
                                    long seconds = value * unit.getToSecond();
                                    MuteManager.mute(getUUID(playername), playername, commandSender.getName(), reason, seconds);
                                    commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().StaffMuteMessage.replace("%player%", playername).replace("%reason%", reason).replace("%enddate%", "until " + MuteManager.getUnbandate(getUUID(playername))));
                                }
                                else
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cYou can only use the following units: §4s (seconds), m (minutes), h (hours), d (days), w (weeks)");
                                }
                            }
                            else
                            {
                                commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().Antimute);
                            }
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().MutePrefix + AdminTools.getInstance().AlreadyMuted);
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tempmute <Player> <Number> <Unit> <Reason>");
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
