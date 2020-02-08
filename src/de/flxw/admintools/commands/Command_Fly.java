package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Fly implements CommandExecutor {
    public Command_Fly(AdminTools adminTools){}
    private static final String PERM_FLY = "admintools.fly";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_FLY) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    if (player.getAllowFlight() == false)
                    {
                        player.setAllowFlight(true);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().FlyMessage);
                    }
                    else
                    {
                        player.setAllowFlight(false);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoFlyMessage);
                    }
                }
                else if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null)
                    {
                        if (!ArrayLists.bypassFly.contains(target) && !ArrayLists.bypassAll.contains(target))
                        {
                            if(target.getAllowFlight() == false)
                            {
                                target.setAllowFlight(true);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoFlyMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherFlyMessage.replaceAll("%player%", target.getName()));
                            }
                            else
                            {
                                target.setAllowFlight(false);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoFlyMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherNoFlyMessage.replaceAll("%player%", target.getName()));
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "fly"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /fly (Player)");
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
