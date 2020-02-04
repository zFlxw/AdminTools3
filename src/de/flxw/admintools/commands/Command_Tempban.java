package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.ban.BanManager;
import de.flxw.admintools.ban.BanUnit;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Command_Tempban implements CommandExecutor {
    public Command_Tempban(AdminTools adminTools) {}

    private static final String PERM_TEMPBAN = "admintools.tempban";
    private static final String PERM_ANTIBAN = "admintools.ban.priority";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_TEMPBAN) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length >= 4)
                {
                    String playername = args[0];
                    long value = 0;
                    if(!BanManager.isBanned(getUUID(playername)))
                    {
                        if(Bukkit.getOfflinePlayer(playername).isOnline())
                        {
                            if(!Bukkit.getPlayer(playername).hasPermission(PERM_ANTIBAN) && !Bukkit.getPlayer(playername).hasPermission(AdminTools.getInstance().PERM_ALL))
                            {
                                try
                                {
                                    value = Integer.valueOf(args[1]);
                                }
                                catch (NumberFormatException ex)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + " §cUsage: /tempban <Player> <Number> <Unit> <Reason>");
                                }
                                if(value >= 500)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + " §cPlease choose a Number under 500!");
                                }

                                String unitString = args[2];
                                String reason = "";

                                StringBuilder sb = new StringBuilder();
                                for(int i = 3; i < args.length; i++)
                                {
                                    sb.append(args[i]).append(" ");
                                }
                                reason = sb.toString().trim();

                                List<String> unitList = BanUnit.getUnitsAsString();
                                if(unitList.contains(unitString.toLowerCase()))
                                {
                                    BanUnit unit = BanUnit.getUnit(unitString);
                                    long seconds = value * unit.getToSecond();
                                    BanManager.ban(getUUID(playername), playername, commandSender.getName(), reason, seconds);
                                    commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Banned.replaceAll("%player%", playername).replace("%reason%", reason).replace("%enddate%", BanManager.getUnbandate(getUUID(playername))));
                                }
                                else
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cYou can only use the following units: §4 s (Seconds), m (Minutes), h (Hours), d (Days), w (Weeks)");
                                }
                            }
                            else
                            {
                                commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Antiban);
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
                                catch (NumberFormatException ex)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + " §cUsage: /tempban <Player> <Number> <Unit> <Reason>");
                                }
                                if(value >= 500)
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + " §cPlease choose a Number under 500!");
                                }

                                String unitString = args[2];
                                String reason = "";

                                StringBuilder sb = new StringBuilder();
                                for(int i = 3; i < args.length; i++)
                                {
                                    sb.append(args[i]).append(" ");
                                }
                                reason = sb.toString().trim();

                                List<String> unitList = BanUnit.getUnitsAsString();
                                if(unitList.contains(unitString.toLowerCase()))
                                {
                                    BanUnit unit = BanUnit.getUnit(unitString);
                                    long seconds = value * unit.getToSecond();
                                    BanManager.ban(getUUID(playername), playername, commandSender.getName(), reason, seconds);
                                    commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Banned.replaceAll("%player%", playername).replace("%reason%", reason).replace("%enddate%", BanManager.getUnbandate(getUUID(playername))));
                                }
                                else
                                {
                                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cYou can only use the following units: §4 s (Seconds), m (Minutes), h (Hours), d (Days), w (Weeks)");
                                }
                            }
                            else
                            {
                                commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().Antiban);
                            }
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().BanPrefix + AdminTools.getInstance().AlreadyBanned);
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tempban <Player> <Number> <Unit> <Reason>");
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
