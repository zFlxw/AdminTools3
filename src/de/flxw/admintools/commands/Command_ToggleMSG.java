package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_ToggleMSG implements CommandExecutor {
    public Command_ToggleMSG(AdminTools adminTools) {
    }
    private static final String PERM_TOGGLEMSG = "admintools.togglemsg";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(player.hasPermission(PERM_TOGGLEMSG) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(ArrayLists.togglemsg.contains(player))
                {
                    ArrayLists.togglemsg.remove(player);
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherToggleMsgMessage);
                }
                else
                {
                    ArrayLists.togglemsg.add(player);
                    player.sendMessage(AdminTools.getInstance().Prefix+AdminTools.getInstance().ToggleMsgMessage);
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
