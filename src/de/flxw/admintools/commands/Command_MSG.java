package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_MSG implements CommandExecutor {
    public Command_MSG(AdminTools adminTools) {}

    private static final String PERM_MSG = "admintools.msg";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_MSG) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length >= 2)
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    String message = "";

                    if(target != null)
                    {
                        if(!target.getName().equalsIgnoreCase(player.getName()))
                        {
                            StringBuilder sb = new StringBuilder();
                            for(int i = 1; i < args.length; i++)
                            {
                                sb.append(args[i]).append(" ");
                            }
                            message = sb.toString().trim();
                            target.sendMessage(AdminTools.getInstance().MsgPrefix + "§c" + player.getName() + " §7➡ §C"+ target.getName()+"§8 »§7 "+ message);
                            player.sendMessage(AdminTools.getInstance().MsgPrefix + "§c" + player.getName() + " §7➡ §C"+ target.getName()+"§8 »§7 "+message);
                            for(Player all : ArrayLists.socialspy)
                            {
                                all.sendMessage(AdminTools.getInstance().SocialspyPrefix + "§c" + player.getName() + " §7➡ §C"+ target.getName()+"§8 »§7 "+message);
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MsgReplyError);
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /msg <Player> <Message>");
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
