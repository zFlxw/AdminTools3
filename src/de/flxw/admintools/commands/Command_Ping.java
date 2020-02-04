package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Command_Ping implements CommandExecutor {
    public Command_Ping(AdminTools adminTools) {
    }

    private static final String PERM_PING = "admintools.ping";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_PING) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                int playerPing = ((CraftPlayer)player).getHandle().ping;
                if(playerPing <= AdminTools.getInstance().PingLightgreen)
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PingMessage.replaceAll("%ping%", "§a" + String.valueOf(playerPing)));
                }
                else if(playerPing <= AdminTools.getInstance().PingDarkgreen)
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PingMessage.replaceAll("%ping%", "§2" + String.valueOf(playerPing)));
                }
                else if(playerPing <= AdminTools.getInstance().PingLightred)
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PingMessage.replaceAll("%ping%", "§c" + String.valueOf(playerPing)));
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PingMessage.replaceAll("%ping%", "§4" + String.valueOf(playerPing)));
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
