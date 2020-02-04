package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class PlayerCloseInventoryListener implements Listener {
    public PlayerCloseInventoryListener(AdminTools adminTools) {}

    @EventHandler
    public static void onInvClose(InventoryCloseEvent evt)
    {
        if(evt.getPlayer() instanceof Player)
        {
            Player player = (Player) evt.getPlayer();
            if(ArrayLists.inventoryIn.containsKey(player))
            {
                if(ArrayLists.inventoryIn.get(player).equals(evt.getInventory()))
                {
                    ArrayLists.inventoryIn.remove(player);
                }
            }
        }
    }
}
