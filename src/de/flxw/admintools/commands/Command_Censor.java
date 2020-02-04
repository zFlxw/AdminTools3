package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Censor implements CommandExecutor {
    public Command_Censor(AdminTools adminTools) {
    }

    private static final String PERM_CENSOR = "admintools.censor";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(player.hasPermission(PERM_CENSOR) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    if(ArrayLists.censor.contains(player))
                    {
                        ArrayLists.censor.remove(player);
                        player.sendMessage(AdminTools.getInstance().Prefix +  AdminTools.getInstance().NoCensorMessage);
                    }
                    else
                    {
                        ArrayLists.censor.add(player);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().CensorMessage);
                    }
                }
                else if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null)
                    {
                        if(!ArrayLists.bypassCensor.contains(target) && !ArrayLists.bypassCensor.contains(player))
                        {
                            if(ArrayLists.censor.contains(target))
                            {
                                ArrayLists.censor.remove(target);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().CensorMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoOtherCensorMessage.replaceAll("%player%", target.getName()));
                            }
                            else
                            {
                                ArrayLists.censor.add(target);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoCensorMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherCensorMessage.replaceAll("%player%", target.getName()));
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "censor"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /censor (player)");
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
