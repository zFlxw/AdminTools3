package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Command_Viewarmor implements CommandExecutor {
    public Command_Viewarmor(AdminTools adminTools) {
    }

    public Command_Viewarmor instance;
    private static final String PERM_VIEWARMOR = "admintools.viewarmor";
    private static final String PERM_ARMOR = "admintools.armor";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        instance = this;
        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(player.hasPermission(PERM_ARMOR) || player.hasPermission(PERM_VIEWARMOR) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);
                    if(target != null)
                    {
                        openInventory(player, target);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /viewarmor <player>");
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

    public static void openInventory(Player player, Player target)
    {
        ItemStack helmet = target.getInventory().getHelmet();
        ItemStack chestplate = target.getInventory().getChestplate();
        ItemStack leggings = target.getInventory().getLeggings();
        ItemStack boots = target.getInventory().getBoots();
        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);

        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        ItemStack barrier = new ItemStack(Material.BARRIER);
        ItemMeta barrierMeta = barrier.getItemMeta();
        barrierMeta.setDisplayName("§cNothing");
        barrier.setItemMeta(barrierMeta);

        Inventory inventory = Bukkit.createInventory(null, 9, "§9Viewarmor");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverifyed access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        inventory.setItem(0, placeholder);
        if(helmet != null)
        {
            inventory.setItem(1, helmet);
        }
        else
        {
            inventory.setItem(1, barrier);
        }
        inventory.setItem(2, placeholder);
        if(chestplate != null)
        {
            inventory.setItem(3, chestplate);
        }
        else
        {
            inventory.setItem(3, barrier);
        }
        inventory.setItem(4, placeholder);
        if(leggings != null)
        {
            inventory.setItem(5, leggings);
        }
        else
        {
            inventory.setItem(5, barrier);
        }
        inventory.setItem(6, placeholder);
        if(boots != null)
        {
            inventory.setItem(7, boots);
        }
        else
        {
            inventory.setItem(7, barrier);
        }
        inventory.setItem(8, placeholder);

        player.openInventory(inventory);
    }

    public Command_Viewarmor getInstance() {
        return instance;
    }
}
