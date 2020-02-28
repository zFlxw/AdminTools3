package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.*;

public class Command_Maintenance implements CommandExecutor
{
    public Command_Maintenance(AdminTools adminTools) {
    }
    public File file = FileManager.getMaintenanceFile();
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    private static final String PERM_MAINTENANCE_HELP = "admintools.maintenance.help";
    private static final String PERM_MAINTENANCE_ON = "admintools.maintenance.on";
    private static final String PERM_MAINTENANCE_OFF = "admintools.maintenance.off";
    private static final String PERM_MAINTENANCE_STATUS = "admintools.maintenance.status";
    private static final String PERM_MAINTENANCE_ADDPLAYER = "admintools.maintenance.addplayer";
    private static final String PERM_MAINTENANCE_REMOVEPLAYER = "admintools.maintenance.removeplayer";
    private static final String PERM_MAINTENANCE_ALL = "admintools.maintenance.*";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) 
    {
        if(args.length == 0)
        {
            //CURRENT STATUS (ON / OFF)
            if(commandSender.hasPermission(PERM_MAINTENANCE_STATUS) || commandSender.hasPermission(PERM_MAINTENANCE_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                cfg = YamlConfiguration.loadConfiguration(file);
                String status = (cfg.getBoolean("maintenance")) ? "§aon" : "§coff";
                commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().StatusMaintenance.replace("%status%", status));
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().NoPerm);
            }
        }
        else if(args.length == 1)
        {
            //HELP | ON | OFF
            if(args[0].equalsIgnoreCase("on"))
            {
                if(commandSender.hasPermission(PERM_MAINTENANCE_ON) || commandSender.hasPermission(PERM_MAINTENANCE_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    cfg = YamlConfiguration.loadConfiguration(file);
                    if(cfg.isSet("maintenance"))
                    {
                        if(!cfg.getBoolean("maintenance"))
                        {
                            String header = AdminTools.getInstance().MaintenanceKickHeader;
                            String line1 = AdminTools.getInstance().MaintenanceKickLine1;
                            String line2 = AdminTools.getInstance().MaintenanceKickLine2;
                            String line3 = AdminTools.getInstance().MaintenanceKickLine3;
                            String footer = AdminTools.getInstance().MaintenanceKickFooter;
                            cfg.set("maintenance", true);
                            try
                            {
                                cfg.save(file);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            for(Player all : Bukkit.getOnlinePlayers())
                            {
                                if(!cfg.isSet("Players."+all.getName().toLowerCase()))
                                {
                                    all.kickPlayer(header + "\n\n\n" + line1 + "\n\n" + line2 + "\n\n" + line3 + "\n\n\n\n\n" + footer);
                                }
                            }
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceOn);
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceAlreadyOn);
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoMaintenanceModeSet);
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args[0].equalsIgnoreCase("off"))
            {
                if(commandSender.hasPermission(PERM_MAINTENANCE_OFF) || commandSender.hasPermission(PERM_MAINTENANCE_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    cfg = YamlConfiguration.loadConfiguration(file);
                    if(cfg.isSet("maintenance"))
                    {
                        if(cfg.getBoolean("maintenance"))
                        {
                            cfg.set("maintenance", false);
                            try
                            {
                                cfg.save(file);
                            }
                            catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceOff);
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceAlreadyOff);
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoMaintenanceModeSet);
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args[0].equalsIgnoreCase("help"))
            {
                if(commandSender.hasPermission(PERM_MAINTENANCE_HELP)|| commandSender.hasPermission(PERM_MAINTENANCE_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    commandSender.sendMessage("§7§m------------------[§c§l Maintenance Help §7§m]------------------");
                    commandSender.sendMessage("§8» §6/maintenance §7| §aChecks if the maintenance is enabled/disabled");
                    commandSender.sendMessage("§8» §6/maintenance on §7| §aenables maintenance");
                    commandSender.sendMessage("§8» §6/maintenance off §7| §adisables maintenance");
                    commandSender.sendMessage("§8» §6/maintenance list §7| §ashows all member on the maintenance bypass list");
                    commandSender.sendMessage("§8» §6/maintenance add <player> §7| §aadd player to 'whitelist'");
                    commandSender.sendMessage("§8» §6/maintenance remove <player> §7| §aremove player from 'whitelist'");
                    commandSender.sendMessage("§7§m------------------[§c§l Maintenance Help §7§m]------------------");
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args[0].equalsIgnoreCase("list"))
            {
                File file = FileManager.getMaintenanceFile();
                FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§2Maintenance Whitelist:");
                for(String key : cfg.getKeys(true))
                {
                    String value = cfg.getString(key);
                    if(key.startsWith("Players."))
                    {
                        String playername = key.substring(8);
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §a" + playername);
                    }
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /maintenance help");
            }
        }
        else if(args.length == 2)
        {
            // ADD | REMOVE |
            if(args[0].equalsIgnoreCase("add"))
            {
                if(commandSender.hasPermission(PERM_MAINTENANCE_ADDPLAYER) || commandSender.hasPermission(PERM_MAINTENANCE_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    cfg = YamlConfiguration.loadConfiguration(file);
                    if(!cfg.isSet("Players."+args[1].toLowerCase()))
                    {
                        cfg.set("Players."+args[1].toLowerCase(), true);
                        try
                        {
                            cfg.save(file);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceAdd.replace("%player%",args[1].toLowerCase()));
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceAlreadySet.replace("%player%",args[1].toLowerCase()));
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("rem"))
            {
                if(commandSender.hasPermission(PERM_MAINTENANCE_REMOVEPLAYER) || commandSender.hasPermission(PERM_MAINTENANCE_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    cfg = YamlConfiguration.loadConfiguration(file);
                    if(cfg.isSet("Players."+args[1].toLowerCase()))
                    {
                        cfg.set("Players."+args[1].toLowerCase(), null);
                        try
                        {
                            cfg.save(file);
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceRemove.replace("%player%",args[1].toLowerCase()));
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenancePlayerNotSet.replace("%player%",args[1].toLowerCase()));
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /maintenance help");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /maintenance help");
        }
        return true;
    }
}
