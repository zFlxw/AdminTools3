package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Tpdeny implements CommandExecutor {
    public Command_Tpdeny(AdminTools adminTools) {
    }

    private static final String PERM_Tpdeny = "admintools.tpdeny";
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_Tpdeny) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    try {
                        Player target = ArrayLists.tpa.get(player);
                        ArrayLists.tpa.remove(player);

                        player.sendMessage(AdminTools.getInstance().Prefix + "§aYou denied the request from §c" + target.getName());
                        target.sendMessage(AdminTools.getInstance().Prefix + "§aYoure request as been denied");
                    }
                    catch(Exception ex)
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cNo open requests");
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /tpdeny");
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
