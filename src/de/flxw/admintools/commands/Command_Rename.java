package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Command_Rename implements CommandExecutor {
    public Command_Rename(AdminTools adminTools) {
    }

    private static final String PERM_RENAME = "admintools.rename";
    private static final String PERM_RENAME_COLOR = "admintools.rename.color";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_RENAME) || player.hasPermission(PERM_RENAME_COLOR) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length >= 1)
                {
                    if(!(player.getItemInHand().getType() == Material.AIR))
                    {
                        ItemStack currentItem = player.getItemInHand();
                        String message = "";
                        StringBuilder sb = new StringBuilder();
                        for(int i = 0; i < args.length; i++)
                        {
                            sb.append(args[i]).append(" ");
                        }
                        message = sb.toString().trim();
                        ItemMeta currentMeta = currentItem.getItemMeta();
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().RenameMessage);
                        if(player.hasPermission(PERM_RENAME_COLOR) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                        {
                            currentMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', message));
                        }
                        else
                        {
                            currentMeta.setDisplayName(message);
                            if(message.contains("&"))
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().RenameColorWarning);
                            }
                        }
                        currentItem.setItemMeta(currentMeta);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().RenameValidItemMessage);
                    }

                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /rename <name>");
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
