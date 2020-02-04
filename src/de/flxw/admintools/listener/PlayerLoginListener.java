package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.ban.BanManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerLoginListener implements Listener {
    public PlayerLoginListener(AdminTools adminTools) {}

    @EventHandler
    public static void onLogin(PlayerLoginEvent evt)
    {
        Player player = evt.getPlayer();
        if(AdminTools.getInstance().MySQLcon)
        {
            if(BanManager.isBanned(player.getUniqueId().toString()))
            {
                long current = System.currentTimeMillis();
                long end = BanManager.getEnd(player.getUniqueId().toString());
                if(current < end || end == -1)
                {
                    String banHeader = AdminTools.getInstance().BanHeader;
                    String banReason = AdminTools.getInstance().BanReason.replaceAll("%reason%", BanManager.getReason(player.getUniqueId().toString()));
                    String bannedby = AdminTools.getInstance().BannedBy.replaceAll("%bannedby%", BanManager.getStaff(player.getUniqueId().toString()));
                    String remaining = AdminTools.getInstance().RemainingBan.replaceAll("%enddate%", BanManager.getUnbandate(player.getUniqueId().toString()));
                    String appeal = AdminTools.getInstance().BanAppeal;
                    evt.disallow(PlayerLoginEvent.Result.KICK_BANNED, banHeader + "\n\n\n" + bannedby + "\n\n" + banReason + "\n\n\n\n\n" + remaining + "\n\n" + appeal);
                }
            }
        }
    }

}
