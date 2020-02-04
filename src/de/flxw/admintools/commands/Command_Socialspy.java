package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Socialspy implements CommandExecutor {
    public Command_Socialspy(AdminTools adminTools) {}

    private static final String PERM_SOCIALSPY = "admintools.socialspy";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_SOCIALSPY) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    if(!ArrayLists.socialspy.contains(player))
                    {
                        ArrayLists.socialspy.add(player);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SocialSpyMessage);
                    }
                    else
                    {
                        ArrayLists.socialspy.remove(player);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().DisableSocialSpyMessage);

                    }
                }
                else if(args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null)
                    {
                        if(!ArrayLists.socialspy.contains(target))
                        {
                            ArrayLists.socialspy.add(target);
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherSocialSpyMessage.replace("%player%", target.getName()));
                            target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SocialSpyMessage);
                        }
                        else
                        {
                            ArrayLists.socialspy.remove(target);
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherDisableSocialSpyMessage.replace("%player%", target.getName()));
                            target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().DisableSocialSpyMessage);

                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /socialspy (Player)");
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
