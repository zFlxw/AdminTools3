package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Invsee implements CommandExecutor {
    public Command_Invsee(AdminTools adminTools) {}
    private static final String PERM_INVSEE = "admintools.invsee";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_INVSEE) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null)
                    {
                        if(!ArrayLists.bypassInvsee.contains(target) &&!ArrayLists.bypassAll.contains(target))
                        {
                            if(!ArrayLists.inventoryIn.containsKey(player))
                            {
                                ArrayLists.inventoryIn.put(player, target.getInventory());
                                player.openInventory(target.getInventory());
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "invsee"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /invsee <Player>");
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
