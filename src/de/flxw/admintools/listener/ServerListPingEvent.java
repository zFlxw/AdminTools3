package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ServerListPingEvent implements Listener {
    public ServerListPingEvent(AdminTools adminTools) {}

    @EventHandler
    public static void onServerPing(org.bukkit.event.server.ServerListPingEvent evt)
    {
        if(AdminTools.getInstance().Modt)
        {
            evt.setMotd(AdminTools.getInstance().ModtLine1 + "\n" + AdminTools.getInstance().ModtLine2);
        }
        evt.setMaxPlayers(AdminTools.getInstance().MaxPlayers);
    }
}
