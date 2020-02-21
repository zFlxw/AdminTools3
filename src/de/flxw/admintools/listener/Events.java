package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Events implements Listener {
    public Events(AdminTools adminTools) {}

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent evt)
    {
        Player player = evt.getPlayer();
        if(ArrayLists.freeze.contains(player))
        {
            if(evt.getFrom().getZ() != evt.getTo().getZ() && evt.getFrom().getX() != evt.getTo().getX())
            {
                Location to = evt.getFrom();
                to.setYaw(evt.getTo().getYaw());
                to.setPitch(evt.getTo().getPitch());
                evt.setTo(to);
                player.teleport(evt.getFrom());
                player.sendTitle(AdminTools.getInstance().FreezeTitle1, AdminTools.getInstance().FreezeTitle2);
            }
        }
    }
    @EventHandler
    public static void onBlockPlace(BlockPlaceEvent evt)
    {
        Player player = evt.getPlayer();
        if(AdminTools.getInstance().DenyBuildWhileFreezed)
        {
            if(ArrayLists.freeze.contains(player))
            {
                evt.setCancelled(true);
            }
        }
    }
    @EventHandler
    public static void onBlockBreak(BlockBreakEvent evt)
    {
        Player player = evt.getPlayer();
        if(AdminTools.getInstance().DenyBuildWhileFreezed)
        {
            if(ArrayLists.freeze.contains(player))
            {
                evt.setCancelled(true);
            }
        }
    }
    @EventHandler
    public static void onPlayerDrop(PlayerDropItemEvent evt)
    {
        Player player = evt.getPlayer();
        if(evt.getItemDrop().getItemStack() != null && evt.getItemDrop().getItemStack().getItemMeta() != null && evt.getItemDrop().getItemStack().getItemMeta().getDisplayName() != null)
        {
            if(evt.getItemDrop().getItemStack().getItemMeta().getDisplayName().equals(AdminTools.getInstance().AdmintoolsItemName) && evt.getItemDrop().getItemStack().getItemMeta().getLore().contains("§0v"+AdminTools.getInstance().getDescription().getVersion()))
            {
                evt.setCancelled(true);
            }
        }
    }
}
