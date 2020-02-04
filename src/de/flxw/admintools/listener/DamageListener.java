package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {
    static AdminTools pl;
    public DamageListener(AdminTools adminTools) {
        this.pl = adminTools;
    }


    @EventHandler
    public static void onPlayerDamage(EntityDamageEvent evt)
    {
        if(evt.getEntity() instanceof  Player)
        {
            Player player = (Player) evt.getEntity();

            if(ArrayLists.godmode.contains(player))
            {
                evt.setCancelled(true);
            }
        }

    }
}
