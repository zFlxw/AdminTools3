package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Command_Skull implements CommandExecutor {
    public Command_Skull(AdminTools adminTools) {
    }

    private static final String PERM_SKULL = "admintools.skull";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(player.hasPermission(PERM_SKULL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 1)
                {
                    String targetname = args[0];
                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwner(targetname);
                    skullMeta.setDisplayName(AdminTools.getInstance().SkullName.replaceAll("%skullowner%", targetname));
                    skull.setItemMeta(skullMeta);
                    player.getInventory().addItem(skull);
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SkullMessage.replace("%skullowner%", targetname));
                }
                else if(args.length == 2)
                {
                    Player target = Bukkit.getPlayer(args[1]);
                    if(target != null)
                    {
                        String targetname = args[0];
                        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                        skullMeta.setOwner(targetname);
                        skullMeta.setDisplayName(AdminTools.getInstance().SkullName.replaceAll("%skullowner%", targetname));
                        skull.setItemMeta(skullMeta);

                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherSkullMessage.replace("%skullowner%", targetname).replace("%player%", target.getName()));
                        target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().SkullMessage.replace("%skullowner%", targetname));

                        target.getInventory().addItem(skull);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cThis player is not online");
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /skull <player> (to player)");
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
