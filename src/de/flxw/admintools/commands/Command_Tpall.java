package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Tpall implements CommandExecutor {
    public Command_Tpall(AdminTools adminTools) {}
    private static final String PERM_TPALL = "admintools.tpall";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_TPALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().TpallMessage);
                    for(Player all : Bukkit.getOnlinePlayers())
                    {
                        all.teleport(player.getLocation());
                        all.sendMessage(AdminTools.getInstance().OtherTpallMessage.replace("%player%", player.getName()));
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tpall");
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
