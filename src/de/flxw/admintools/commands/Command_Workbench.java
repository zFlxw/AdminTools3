package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Workbench implements CommandExecutor {
    public Command_Workbench(AdminTools adminTools) {
    }

    private static final String PERM_Workbench = "admintools.workbench";
    private static final String PERM_WB = "admintools.wb";


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_WB) || player.hasPermission(PERM_Workbench) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    player.openWorkbench(player.getLocation(), true);
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().WorkbenchMessage);
                }
                else if(args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null)
                    {
                        target.openWorkbench(target.getLocation(), true);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherWorkbenchMessage.replace("%player%", target.getName()));
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /workbench (Player)");
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
