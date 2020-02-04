package de.flxw.admintools.listener;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class CommandPreprocessListener implements Listener {
    public CommandPreprocessListener(AdminTools adminTools) {}

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        if(AdminTools.getInstance().UnknownCommandBoolean)
        {
            if (!event.isCancelled())
            {
                Player player = event.getPlayer();
                String command = event.getMessage().split(" ")[0];
                HelpTopic helpTopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);

                if (helpTopic == null)
                {
                    player.sendMessage(AdminTools.getInstance().UnknownCommand.replace("%command%", command));
                    event.setCancelled(true);
                }
            }
        }
    }
}
