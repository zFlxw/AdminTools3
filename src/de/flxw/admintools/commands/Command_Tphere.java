package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Tphere implements CommandExecutor {
    public Command_Tphere(AdminTools adminTools) {}
    private static final String PERM_TPHERE = "admintools.tphere";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_TPHERE) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null)
                    {
                        target.teleport(player.getLocation());
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().TphereMessage.replace("%player%", target.getName()));
                        target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherTpacceptMessage.replace("%player%", player.getName()));
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage((AdminTools.getInstance().Prefix + "§cUsage: /tphere <player>"));
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
