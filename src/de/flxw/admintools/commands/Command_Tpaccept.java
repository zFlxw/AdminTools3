package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Tpaccept implements CommandExecutor {
    private static final String PERM_TPACCEPT = "admintools.tpaccept";
    public Command_Tpaccept(AdminTools adminTools) {
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_TPACCEPT) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    try {
                        Player target = ArrayLists.tpa.get(player);
                        ArrayLists.tpa.remove(player);

                        target.teleport(player.getLocation());
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().TpacceptMessage.replace("%player%", target.getName()));
                        target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherTpacceptMessage);
                    }
                    catch(Exception ex)
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cNo open requests");
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tpaccept");
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
