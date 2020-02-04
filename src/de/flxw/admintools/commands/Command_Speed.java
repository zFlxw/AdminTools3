package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Speed implements CommandExecutor {
    public Command_Speed(AdminTools adminTools) {
    }

    private static final String PERM_SPEED = "admintools.speed";
    private static final String PERM_SPEED_OTHER = "admintools.speed.other";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(args.length == 1)
            {
                if(player.hasPermission(PERM_SPEED) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    try {
                        int speed = Integer.valueOf(args[0]);
                        float finalspeed = 0;
                        if(speed <= 10)
                        {
                            switch(speed)
                            {
                                case 1:
                                    finalspeed = 0.1f;
                                    break;
                                case 2:
                                    finalspeed = 0.2f;
                                    break;
                                case 3:
                                    finalspeed = 0.3f;
                                    break;
                                case 4:
                                    finalspeed = 0.4f;
                                    break;
                                case 5:
                                    finalspeed = 0.5f;
                                    break;
                                case 6:
                                    finalspeed = 0.6f;
                                    break;
                                case 7:
                                    finalspeed = 0.7f;
                                    break;
                                case 8:
                                    finalspeed = 0.8f;
                                    break;
                                case 9:
                                    finalspeed = 0.9f;
                                    break;
                                case 10:
                                    finalspeed = 1f;
                                    break;
                            }
                            player.setWalkSpeed(finalspeed);
                            player.setFlySpeed(finalspeed);
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SpeedMessage.replace("%speed%", String.valueOf(speed)));
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + "§cPlease choose a number under 10");
                        }
                    }
                    catch(NumberFormatException ex)
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SpeedValidMessage);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args.length == 2)
            {
                if(player.hasPermission(PERM_SPEED_OTHER) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    try {
                        int speed = Integer.valueOf(args[0]);
                        Player target = Bukkit.getPlayer(args[1]);
                        if(target != null)
                        {
                            if(!ArrayLists.bypassSpeed.contains(target) && !ArrayLists.bypassAll.contains(player))
                            {
                                if(speed <= 10)
                                {
                                    float finalspeed = 0;
                                    switch(speed)
                                    {
                                        case 1:
                                            finalspeed = 0.1f;
                                            break;
                                        case 2:
                                            finalspeed = 0.2f;
                                            break;
                                        case 3:
                                            finalspeed = 0.3f;
                                            break;
                                        case 4:
                                            finalspeed = 0.4f;
                                            break;
                                        case 5:
                                            finalspeed = 0.5f;
                                            break;
                                        case 6:
                                            finalspeed = 0.6f;
                                            break;
                                        case 7:
                                            finalspeed = 0.7f;
                                            break;
                                        case 8:
                                            finalspeed = 0.8f;
                                            break;
                                        case 9:
                                            finalspeed = 0.9f;
                                            break;
                                        case 10:
                                            finalspeed = 1f;
                                            break;
                                    }
                                    target.setWalkSpeed(finalspeed);
                                    target.setFlySpeed(finalspeed);
                                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherSpeedMessage.replace("%player%", target.getName()).replace("%speed%", String.valueOf(speed)));
                                    target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SpeedMessage.replace("%speed%", String.valueOf(speed)));
                                }
                                else
                                {
                                    player.sendMessage(AdminTools.getInstance().Prefix + "§cPlease choose a number under 10");
                                }
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "speed"));
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                        }
                    }
                    catch(NumberFormatException ex)
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SpeedValidMessage);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else
            {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /speed <amount> (player)");
            }

        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPlayer);
        }

        return true;
    }
}
