package de.flxw.admintools.listener;

import de.flxw.admintools.commands.Command_Lockchat;
import de.flxw.admintools.mute.MuteManager;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

public class ChatListener implements Listener {
    public ChatListener(AdminTools adminTools) {}

    @EventHandler
    public static void onChat(AsyncPlayerChatEvent evt)
    {
        Player player = evt.getPlayer();
        if(MuteManager.isMuted(player.getUniqueId().toString()))
        {
            long current = System.currentTimeMillis();
            long end = MuteManager.getEnd(player.getUniqueId().toString());

            if(current < end || end == -1)
            {
                String muteMessage = AdminTools.getInstance().DisallowMessage.replaceAll("%reason%", MuteManager.getReason(player.getUniqueId().toString()));
                muteMessage = muteMessage.replace("%enddate%", MuteManager.getUnbandate(player.getUniqueId().toString()));
                player.sendMessage(muteMessage);
                evt.setCancelled(true);
            }
            else
            {
                MuteManager.unmute(player.getUniqueId().toString());
            }
        }

        if(Command_Lockchat.isChatLocked == true)
        {
            if(!ArrayLists.bypassLockchat.contains(player) && !ArrayLists.bypassAll.contains(player ))
            {
                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().LockchatDenyMessage);
                evt.setCancelled(true);
            }
        }

        if(ArrayLists.censor.contains(player))
        {
            String msg = evt.getMessage();
            for(Player all : Bukkit.getOnlinePlayers())
            {
                evt.setFormat("§c§k" + player.getDisplayName()+ "§r§8» §7" + msg);
                if(ArrayLists.bypassCensor.contains(all))
                {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            all.sendMessage(AdminTools.getInstance().Prefix + "§cRealname: " + player.getDisplayName());
                        }
                    }, 100);
                }
            }
        }
    }

}
