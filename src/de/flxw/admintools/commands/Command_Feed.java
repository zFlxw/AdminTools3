package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Feed implements CommandExecutor {
    public Command_Feed(AdminTools adminTools) {}
    private static final String PERM_FEED = "admintools.feed";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_FEED) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    player.setFoodLevel(20);
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().FeedMessage);
                }
                else if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(args[0].equals("*"))
                    {
                        for (Player all: Bukkit.getOnlinePlayers()) {
                            if(!ArrayLists.bypassFeed.contains(all) && !ArrayLists.bypassAll.contains(all))
                            {
                                all.setFoodLevel(20);
                                all.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().FeedMessage);

                            }
                        }
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AllFeedMessage);
                    }
                    else
                    {
                        if(target != null)
                        {
                            if(!ArrayLists.bypassFeed.contains(target) &&!ArrayLists.bypassAll.contains(target))
                            {
                                target.setFoodLevel(20);
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().FeedMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherFeedMessage.replaceAll("%player%", target.getName()));
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "feed"));
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                        }
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /feed (Player)");
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
