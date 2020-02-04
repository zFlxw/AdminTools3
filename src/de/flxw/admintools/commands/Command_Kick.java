package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Kick implements CommandExecutor {
    public Command_Kick(AdminTools adminTools) {}

    private static final String PERM_KICK = "admintools.kick";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_KICK) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length >= 2)
            {
                Player target = Bukkit.getPlayer(args[0]);
                String reason = "";

                StringBuilder sb = new StringBuilder();
                for(int i = 1; i < args.length; i++)
                {
                    sb.append(args[i]).append(" ");
                }
                reason = sb.toString().trim();
                if(args[0].equalsIgnoreCase("*"))
                {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        if(!all.getName().equalsIgnoreCase(commandSender.getName()))
                        {
                            if(!all.hasPermission(PERM_KICK) || !all.hasPermission(AdminTools.getInstance().PERM_ALL))
                            {
                                String kickHeader = AdminTools.getInstance().KickHeader;
                                String kickReason = AdminTools.getInstance().KickReason.replaceAll("%reason%", reason);
                                String kickedBy = AdminTools.getInstance().KickedBy.replaceAll("%kickedby%", commandSender.getName());
                                all.kickPlayer(kickHeader + "\n\n\n" + kickReason + "\n\n" + kickedBy);
                            }
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AllKicked);
                        }
                    }
                }
                else
                {
                    if(target != null)
                    {
                        String kickHeader = AdminTools.getInstance().KickHeader;
                        String kickReason = AdminTools.getInstance().KickReason.replaceAll("%reason%", reason);
                        String kickedBy = AdminTools.getInstance().KickedBy.replaceAll("%kickedby%", commandSender.getName());
                        target.kickPlayer(kickHeader + "\n\n\n" + kickReason + "\n\n" + kickedBy);
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /kick <Player> <Reason>");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }

        return true;
    }
}
