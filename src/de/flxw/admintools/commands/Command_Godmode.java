package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Godmode implements CommandExecutor {
    public Command_Godmode(AdminTools adminTools) {}
    private static final String PERM_GODMODE = "admintools.godmode";
    private static final String PERM_GOD = "admintools.god";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_GODMODE) || player.hasPermission(PERM_GOD) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    if (!ArrayLists.godmode.contains(player))
                    {
                        ArrayLists.godmode.add(player);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GodmodeMessage);
                    }
                    else
                    {
                        ArrayLists.godmode.remove(player);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoGodmodeMessage);
                    }
                }
                else if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null)
                    {
                        if(!ArrayLists.bypassGodmode.contains(target) && !ArrayLists.bypassAll.contains(target))
                        {
                            if (!ArrayLists.godmode.contains(target))
                            {
                                ArrayLists.godmode.add(target);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GodmodeMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherGodmodeMessage.replaceAll("%player%", target.getName()));
                            }
                            else
                            {
                                ArrayLists.godmode.remove(target);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoGodmodeMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoLongerGodmodeOther.replaceAll("%player%", target.getName()));
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "godmode"));
                        }
                    }
                    else
                    {
                        player.sendMessage( AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /godmode (Player)");
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
