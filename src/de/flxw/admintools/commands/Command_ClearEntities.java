package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class Command_ClearEntities implements CommandExecutor
{
    public Command_ClearEntities(AdminTools adminTools) {}
    private static final String PERM_CLEARENTITIES_ALL = "admintools.clearentities.*";
    private static final String PERM_CLEARENTITIES_CURRENT = "admintools.clearentities.current";
    private static final String PERM_CLEARENTITIES_ALLWORLDS = "admintools.clearentities.allworlds";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
         Player player = (Player) commandSender;
        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("current"))
            {
                if(player.hasPermission(PERM_CLEARENTITIES_CURRENT) || player.hasPermission(PERM_CLEARENTITIES_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    World world = player.getWorld();
                    int count = 0;
                    for(Entity entity : world.getEntities())
                    {
                        if(!(entity instanceof Player || entity instanceof Item))
                        {
                            entity.remove();
                            count++;
                        }
                    }
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().CurrentWorldClearMessage.replace("%count%",String.valueOf(count)));
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args[0].equalsIgnoreCase("all"))
            {
                if(player.hasPermission(PERM_CLEARENTITIES_ALLWORLDS) || player.hasPermission(PERM_CLEARENTITIES_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    int count = 0;
                    for(World world : Bukkit.getServer().getWorlds())
                    {
                        for(Entity entity : world.getEntities())
                        {
                            if(!(entity instanceof Player || entity instanceof Item))
                            {
                                entity.remove();
                                count++;
                            }
                        }
                    }
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AllWorldClearMessage.replace("%count%",String.valueOf(count)));
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else
            {
                player.sendMessage("§c[DEVBUILD] Usage: /clearentites <current | all>");
            }
        }
        else
        {
            commandSender.sendMessage("§c[DEVBUILD] Usage: /clearentites <current | all>");
        }
        return true;
    }
}
