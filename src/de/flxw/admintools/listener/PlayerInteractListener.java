package de.flxw.admintools.listener;

import de.flxw.admintools.utils.ATCenterInv;
import de.flxw.admintools.utils.AdminTools;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteractListener implements Listener {
    public PlayerInteractListener(AdminTools adminTools) {}
    private static final String PERM_ATGUI = "admintools.atgui";

    @EventHandler
    public static void onPlayerInteract(PlayerInteractEvent evt)
    {
        Player player = evt.getPlayer();
        if(evt.getAction() != null)
        {
            if(evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK)
            {
                if(evt.getItem() != null && evt.getItem().getItemMeta() != null && evt.getItem().getItemMeta().getDisplayName() != null)
                {
                    if(evt.getItem().getItemMeta().getDisplayName().equals(AdminTools.getInstance().AdmintoolsItemName) && evt.getItem().getItemMeta().getLore().contains("§0v"+AdminTools.getInstance().getDescription().getVersion()))
                    {
                        if(player.hasPermission(PERM_ATGUI) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                        {
                            ATCenterInv.AdminMainInv(player);
                        }
                    }
                }
            }
        }
    }
}
