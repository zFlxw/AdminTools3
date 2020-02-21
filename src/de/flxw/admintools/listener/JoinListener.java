package de.flxw.admintools.listener;

import de.flxw.admintools.mysql.MySQL;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.ban.BanManager;
import de.flxw.admintools.utils.ArrayLists;
import de.flxw.admintools.utils.PlayerInfoManager;
import de.flxw.admintools.utils.UpdateChecker;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class JoinListener implements Listener {
    public JoinListener(AdminTools adminTools) {}
    private static String PERM_TEAMJOIN = "admintools.teamjoin";
    private static String PERM_UPDATEINFO = "admintools.updateinfo";
    private static String PERM_ATGUI = "admintools.atgui";

    @EventHandler
    public static void onJoin(PlayerJoinEvent evt)
    {
        Player player = evt.getPlayer();
        String PlayerJoinMessage = AdminTools.getInstance().JoinMessage.replaceAll("%player%", player.getName());
        String PlayerTeamJoinMessage = AdminTools.getInstance().JoinMessageTeam.replaceAll("%player%", player.getName());
        ArrayLists.togglemsg.add(player);

        if(AdminTools.getInstance().GetAdmintoolsItemOnSpawn)
        {
            if(player.hasPermission(PERM_ATGUI) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                ItemStack atItem = new ItemStack(Material.COMPASS);
                ItemMeta atItemMeta = atItem.getItemMeta();
                atItemMeta.setDisplayName(AdminTools.getInstance().AdmintoolsItemName);
                atItemMeta.setLore(Arrays.asList("§0v"+ AdminTools.getInstance().getDescription().getVersion()));
                atItem.setItemMeta(atItemMeta);
                if(!player.getInventory().contains(atItem))
                {
                    player.getInventory().addItem(atItem);
                }
            }
        }
        if(AdminTools.getInstance().MySQLcon)
        {
            if(PlayerInfoManager.isPlayerInTable(player.getUniqueId().toString()))
            {
                String playername = player.getName();
                String uuid = player.getUniqueId().toString();
                String ip = PlayerInfoManager.getPlayerIp(uuid);
                String first_joined = PlayerInfoManager.getFirstJoined(uuid);
                long last_seen = System.currentTimeMillis();
                MySQL.update("DELETE FROM PlayerInfo WHERE UUID='"+uuid+"'");

                if(AdminTools.getInstance().LogIP)
                {
                    MySQL.update("INSERT INTO PlayerInfo (PLAYERNAME, UUID, IP_ADDRESS, FIRST_JOINED, LAST_JOINED) VALUES ('"+playername+"','"+uuid+"','"+ip+"','"+first_joined+"','"+last_seen+"')");
                }
                else
                {
                    MySQL.update("INSERT INTO PlayerInfo (PLAYERNAME, UUID, FIRST_JOINED, LAST_JOINED) VALUES ('"+playername+"','"+uuid+"','"+first_joined+"','"+last_seen+"')");
                }
            }
            else
            {
                String playername = player.getName();
                String uuid = player.getUniqueId().toString();
                String ip = player.getAddress().getAddress().getHostAddress();
                long first_joined = System.currentTimeMillis();
                long last_seen = System.currentTimeMillis();

                if(AdminTools.getInstance().LogIP)
                {
                    MySQL.update("INSERT INTO PlayerInfo (PLAYERNAME, UUID, IP_ADDRESS, FIRST_JOINED, LAST_JOINED) VALUES ('"+playername+"','"+uuid+"','"+ip+"','"+first_joined+"','"+last_seen+"')");
                }
                else
                {
                    MySQL.update("INSERT INTO PlayerInfo (PLAYERNAME, UUID, FIRST_JOINED, LAST_JOINED) VALUES ('"+playername+"','"+uuid+"','"+first_joined+"','"+last_seen+"')");
                }
            }
        }
        if(!AdminTools.getInstance().DisableUpdateMessage) {
            if(player.hasPermission(PERM_UPDATEINFO) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                new UpdateChecker(AdminTools.getInstance(), AdminTools.getInstance().resourceId).getVersion(version -> {
                    if(!AdminTools.getInstance().getDescription().getVersion().equalsIgnoreCase(version))
                    {
                        TextComponent tc = new TextComponent();
                        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/admintools-mysql.68455/"));
                        tc.setText(AdminTools.getInstance().Prefix + "§6newer version available. Click here to download");
                        player.spigot().sendMessage(tc);
                        player.sendMessage(AdminTools.getInstance().Prefix + "§6gladly you can also rate my plugin on spigotmc");
                    }
                });

            }
        }

        if(AdminTools.getInstance().MySQLcon)
        {
            if(BanManager.isBanned(player.getUniqueId().toString()))
            {
                BanManager.unban(player.getUniqueId().toString());
            }
        }
        for(Player vanished : ArrayLists.vanish)
        {
            player.hidePlayer(vanished);
        }
        if(player.getGameMode().equals(GameMode.CREATIVE))
        {
            player.setAllowFlight(true);
        }
        if(player.hasPermission(PERM_TEAMJOIN) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            evt.setJoinMessage(PlayerTeamJoinMessage);
        }
        else
        {
            evt.setJoinMessage(PlayerJoinMessage);
        }
    }

    @EventHandler
    public static void onQuit(PlayerQuitEvent evt)
    {
        Player player = evt.getPlayer();
        String PlayerQuitMessage = AdminTools.getInstance().QuitMessage.replaceAll("%player%", player.getName());

        evt.setQuitMessage(PlayerQuitMessage);
    }

}
