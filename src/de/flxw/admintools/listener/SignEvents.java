package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SignEvents implements Listener {
    public SignEvents(AdminTools adminTools) {
    }

    private static final String PERM_FREESIGN_CREATE = "admintools.freesign.create";
    private static final String PERM_FREESIGN_USE = "admintools.freesign.use";
    private static final String PERM_FREESIGN_ALL = "admintools.freesign.*";

    @EventHandler
    public static void SignChangeEvent(SignChangeEvent evt)
    {
        Player player = evt.getPlayer();

        if(player.hasPermission(PERM_FREESIGN_CREATE) || player.hasPermission(PERM_FREESIGN_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(evt.getLine(0).equalsIgnoreCase("[FREE]"))
            {
                evt.setLine(0, "§8«§9§lFREE§8»");
            }
            else if(evt.getLine(1).equalsIgnoreCase(""))
            {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cplease enter a valid ID!");
            }
            else if(!evt.getLine(2).equalsIgnoreCase(""))
            {
                evt.setLine(2, ChatColor.translateAlternateColorCodes('&', evt.getLine(2)));
            }
        }
    }
    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent evt)
    {
        Player player = evt.getPlayer();

        if(evt.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(evt.getClickedBlock().getState() instanceof Sign)
            {
                Sign sign = (Sign) evt.getClickedBlock().getState();

                if(sign.getLine(0).equals("§8«§9§lFREE§8»"))
                {
                    try
                    {
                        String[] string = sign.getLine(1).split(":");
                        String name = sign.getLine(2);
                        String colorname = ChatColor.translateAlternateColorCodes('&', name);
                        int ID = Integer.valueOf(string[0]);
                        int Byte = 0;

                        if(sign.getLine(1).contains(":"))
                        {
                            Byte = Integer.valueOf(string[1]);
                        }

                        Inventory inv = Bukkit.createInventory(null, 27, "§aFreeSign");
                        boolean fail = false;
                        for(int i = 0; i < inv.getSize(); i++)
                        {
                            ItemStack item = new ItemStack(ID, 64, (byte) Byte);
                            ItemMeta itemMeta = item.getItemMeta();
                            if(itemMeta != null)
                            {
                                itemMeta.setDisplayName(colorname);
                                item.setItemMeta(itemMeta);
                                inv.setItem(i, item);
                            }
                            else
                            {
                                fail=true;
                            }
                        }
                        if(fail)
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + "§cThis item couldn't be found");
                            return;
                        }
                        player.openInventory(inv);

                    }
                    catch(NumberFormatException ex)
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§aNo valid ID (§c" + sign.getLine(1) + "§a)");
                    }
                }
            }
        }
    }
}
