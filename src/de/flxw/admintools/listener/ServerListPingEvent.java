package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;

public class ServerListPingEvent implements Listener
{
    public ServerListPingEvent(AdminTools adminTools) {}
    @EventHandler
    public static void onServerPing(org.bukkit.event.server.ServerListPingEvent evt)
    {
        File file = FileManager.getMaintenanceFile();
        FileConfiguration cfg;

        if(AdminTools.getInstance().Modt)
        {
            cfg = YamlConfiguration.loadConfiguration(file);
            if(cfg.getBoolean("maintenance"))
            {
                evt.setMotd(AdminTools.getInstance().MaintenanceMotdLine1 + "\n" + AdminTools.getInstance().MaintenanceMotdLine2);
            }
            else
            {
                evt.setMotd(AdminTools.getInstance().ModtLine1 + "\n" + AdminTools.getInstance().ModtLine2);
            }
        }
        evt.setMaxPlayers(AdminTools.getInstance().MaxPlayers);
    }
}
