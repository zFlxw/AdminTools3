//CURRENTLY IN DEVELOPMENT BUILD. NOT RELEASED / WORKING YET
package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command_Hat implements CommandExecutor {
    public Command_Hat(AdminTools adminTools) {}
    private static final String PERM_HAT = "admintools.hat";
    private static final String PERM_HAT_OTHER = "admintools.hat.other";
    private static final String PERM_HAT_ALL = "admintools.hat.*";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(args.length == 0)
            {
                if(player.hasPermission(PERM_HAT) || player.hasPermission(PERM_HAT_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {
                    ItemStack itemInHand = player.getItemInHand();
                    if(itemInHand.getType() != Material.AIR && itemInHand != null)
                    {
                        ItemStack helmet = player.getInventory().getHelmet();
                        if(helmet != null && helmet.getType() != Material.AIR)
                        {
                            player.getInventory().addItem(helmet);
                            player.getInventory().getHelmet().setType(Material.AIR);
                            player.getInventory().setHelmet(itemInHand);
                            player.getInventory().getItemInHand().setType(Material.AIR);
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().HatMessage.replaceAll("%item%", itemInHand.getItemMeta().getDisplayName()));
                        }
                        else
                        {
                            player.getInventory().setHelmet(itemInHand);
                            player.getInventory().getItemInHand().setType(Material.AIR);
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().NoLongerHatMessage.replace("%item%", itemInHand.getItemMeta().getDisplayName()));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().HatErrorMessage);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
            else if(args.length == 1)
            {
                if(player.hasPermission(PERM_HAT_OTHER) || player.hasPermission(PERM_HAT_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                {

                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().NoPerm);
                }
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPlayer);
        }
        return true;
    }
}
