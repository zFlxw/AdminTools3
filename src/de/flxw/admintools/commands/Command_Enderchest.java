package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Enderchest implements CommandExecutor {
    public Command_Enderchest(AdminTools adminTools) {}
    private static final String PERM_ENDERCHEST = "admintools.enderchest";
    private static final String PERM_ENDERCHEST_OTHER = "admintools.enderchest.other";
    private static final String PERM_ENDERCHEST_ALL = "admintools.enderchest.*";
    private static final String PERM_EC = "admintools.ec";
    private static final String PERM_EC_OTHER = "admintools.ec.other";
    private static final String PERM_EC_ALL = "admintools.ec.*";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_EC) || player.hasPermission(PERM_ENDERCHEST) || player.hasPermission(PERM_ENDERCHEST_ALL) || player.hasPermission(PERM_EC_ALL)|| player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    player.openInventory(player.getEnderChest());
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().EnderchestMessage);
                }
                else if(args.length == 1)
                {
                    if(player.hasPermission(PERM_EC_OTHER) || player.hasPermission(PERM_EC_ALL) || player.hasPermission(PERM_ENDERCHEST_OTHER) || player.hasPermission(PERM_ENDERCHEST_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        Player target = Bukkit.getPlayer(args[0]);
                        player.openInventory(target.getEnderChest());
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherEnderchestMessage.replaceAll("%player%", target.getName()));
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /enderchest (Player)");
                }
            }
            else
            {
                player.hasPermission(AdminTools.getInstance().NoPerm);
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPlayer);
        }
        return true;
    }
}
