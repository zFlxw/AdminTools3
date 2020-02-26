package de.flxw.admintools.listener;

import de.flxw.admintools.commands.Command_Checkban;
import de.flxw.admintools.commands.Command_Checkmute;
import de.flxw.admintools.commands.Command_Lockchat;
import de.flxw.admintools.utils.ATCenterInv;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import de.flxw.admintools.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.*;

public class InventoryListener implements Listener {
    public InventoryListener(AdminTools adminTools) {}
    public static String PLUGIN_VERSION = AdminTools.getInstance().getDescription().getVersion();

    @EventHandler
    public static void onInvClick(InventoryClickEvent evt)
    {
        Player player = (Player) evt.getWhoClicked();
        if(evt.getClickedInventory() != null && evt.getWhoClicked() != null)
        {
            if (evt.getClickedInventory().getName().equals("§c§lTime"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§6§lDay") && evt.getCurrentItem() != null)
                        {
                            player.getLocation().getWorld().setTime(7000);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aIt's now §eday §afor you!");
                            player.closeInventory();
                            evt.setCancelled(true);
                        }
                        else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§0§lNight"))
                        {
                            player.getLocation().getWorld().setTime(15000);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aIt's now §0night §afor you!");
                            player.closeInventory();
                            evt.setCancelled(true);
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /ptime");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lWeather"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§e§lSun") && evt.getCurrentItem() != null)
                        {
                            player.getLocation().getWorld().setStorm(false);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aIt's now §esunny §afor you!");
                            player.closeInventory();
                            evt.setCancelled(true);
                        }
                        else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9§lRain"))
                        {
                            player.getLocation().getWorld().setStorm(true);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aIt's now §9rainy §afor you!");
                            player.closeInventory();
                            evt.setCancelled(true);
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /pweather");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§4§lAT-Center §8§l| §9" + PLUGIN_VERSION))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    Timer timer = new Timer();
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lClose"))
                        {
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§f§lCommands"))
                        {
                            player.closeInventory();
                            ATCenterInv.AdminCommandsInv(player);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§aPlayer Control"))
                        {
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminPlayerControlInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§cServer Control"))
                        {
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminServerControlInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§7Item"))
                        {
                            player.closeInventory();
                            ItemStack atItem = new ItemStack(Material.COMPASS);
                            ItemMeta atItemMeta = atItem.getItemMeta();
                            atItemMeta.setDisplayName(AdminTools.getInstance().AdmintoolsItemName);
                            atItemMeta.setLore(Arrays.asList("§0v"+ AdminTools.getInstance().getDescription().getVersion()));
                            atItem.setItemMeta(atItemMeta);

                            if(!player.getInventory().contains(atItem))
                            {
                                player.getInventory().addItem(atItem);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdmintoolsItemAdded);
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdmintoolsItemAlreadyInInventory);
                            }
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
                evt.setCancelled(true);
            }
            else if(evt.getClickedInventory().getName().equals("§cAT Player Control §7| §9" + PLUGIN_VERSION))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    Timer timer = new Timer();
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§cKill Player"))
                        {
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminKillInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§cGamemodes"))
                        {
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminGamemodeInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Survival"))
                        {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Creative"))
                        {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Adventure"))
                        {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Spectator"))
                        {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                            player.closeInventory();
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§cAT Server Control §7| §9" + PLUGIN_VERSION))
            {
                if(evt.getCurrentItem().getItemMeta() != null && evt.getCurrentItem() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lOn (Whitelist)")) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "whitelist on");
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aWhitelist on");
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lOff (Whitelist)")) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "whitelist off");
                            player.sendMessage(AdminTools.getInstance().Prefix + "§cWhitelist off");
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§2Add Player (Whitelist)")) {
                            Timer timer = new Timer();
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminAddPlayerInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemove Player (Whitelist)")) {
                            Timer timer = new Timer();
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminRemPlayerInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Whitelisted Players (Whitelist)"))
                        {
                            player.closeInventory();

                            player.sendMessage("§7-----> §aWhitelisted Players §7<-----");
                            for(OfflinePlayer wl : Bukkit.getServer().getWhitelistedPlayers()) {
                                player.sendMessage("§7- §9" + wl.getName());
                            }
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§a§lOn (Maintenance)")) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "maintenance on");
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceOn);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lOff (Maintenance)")) {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "maintenance off");
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceOff);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§2Add Player (Maintenance)")) {
                            Timer timer = new Timer();
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminMaintenanceAddPlayerInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§cRemove Player (Maintenance)")) {
                            Timer timer = new Timer();
                            player.closeInventory();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminMaintenanceRemPlayerInv(player);
                                }
                            }, 300);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Whitelisted Players (Maintenance)"))
                        {
                            player.closeInventory();
                            Bukkit.getServer().dispatchCommand(player, "maintenance list");
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lBack")) {
                            player.closeInventory();
                            Timer timer = new Timer();
                            timer.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    ATCenterInv.AdminMainInv(player);
                                }
                            }, 300);
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Commands §7§l| §9Page 1"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lBack"))
                        {
                            player.closeInventory();
                            ATCenterInv.AdminMainInv(player);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lGamemode"))
                        {
                            player.closeInventory();
                            ATCenterInv.AdminGamemodeInv(player);
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lFly"))
                        {
                            if(player.getAllowFlight() == false)
                            {
                                player.setAllowFlight(true);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou can §2now §afly");
                            }
                            else
                            {
                                player.setAllowFlight(false);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou can §cno longer §afly");
                            }
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lGodmode"))
                        {
                            if(!ArrayLists.godmode.contains(player))
                            {
                                ArrayLists.godmode.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §ainvincible");
                            }
                            else
                            {
                                ArrayLists.godmode.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §ainvincible");
                            }
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lHeal"))
                        {
                            player.setHealth(20);
                            player.setFoodLevel(20);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou've been healed!");
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lFeed"))
                        {
                            player.setFoodLevel(20);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou've been fed!");
                            player.closeInventory();
                        }
                        else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lInvsee"))
                        {
                            ATCenterInv.AdminInvseePlayersInv(player);
                        }
                        else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lClearChat"))
                        {
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                for(int i = 0; i < 100; i++) {
                                    all.sendMessage(" ");
                                }
                                all.sendMessage(AdminTools.getInstance().Prefix + "§3The Chat was cleared by §c" + player.getDisplayName());
                            }
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lLockchat"))
                        {
                            if(Command_Lockchat.isChatLocked == false) {
                                Command_Lockchat.isChatLocked = true;
                                for(Player all : Bukkit.getOnlinePlayers()) {
                                    all.sendMessage(AdminTools.getInstance().Prefix + "§cThe chat was locked by §2" + player.getName());
                                }
                            }
                            else {
                                Command_Lockchat.isChatLocked = false;
                                for(Player all : Bukkit.getOnlinePlayers()) {
                                    all.sendMessage(AdminTools.getInstance().Prefix + "§cThe chat was unlocked by §2" + player.getName());
                                }
                            }
                            player.closeInventory();
                        }
                        else if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lBypass"))
                        {
                            player.closeInventory();
                            ATCenterInv.AdminBypassInv(player);
                        }
                    }
                    evt.setCancelled(true);
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                    player.closeInventory();
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Gamemode §7§l| §9" + PLUGIN_VERSION))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Survival"))
                        {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                        }
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Creative"))
                        {
                            player.setGameMode(GameMode.CREATIVE);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                        }
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Adventure"))
                        {
                            player.setGameMode(GameMode.ADVENTURE);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                        }
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Spectator"))
                        {
                            player.setGameMode(GameMode.SPECTATOR);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're now in Gamemode §c" + player.getGameMode());
                        }
                        player.closeInventory();
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lBack"))
                        {
                            ATCenterInv.AdminCommandsInv(player);
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }

            }
            else if(evt.getClickedInventory().getName().equals("§cOnline players §7| §9Invsee"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if (evt.getCurrentItem().getType() == Material.SKULL_ITEM)
                        {
                            String playername = evt.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§9", "");
                            if (!ArrayLists.bypassInvsee.contains(Bukkit.getPlayer(playername))) {
                                player.openInventory(Bukkit.getPlayer(playername).getInventory());
                            } else {
                                player.sendMessage(AdminTools.getInstance().Prefix + "§cThis player is bypassing Invsee!");
                            }

                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
                evt.setCancelled(true);

            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Bypass §7| §9Page 1"))
            {
                if (evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null) {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9GameMode")) {
                            if (!ArrayLists.bypassGamemode.contains(player)) {

                                ArrayLists.bypassGamemode.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cGamemode");
                            } else {
                                ArrayLists.bypassGamemode.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cGamemode");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Fly")) {
                            if (!ArrayLists.bypassFly.contains(player)) {

                                ArrayLists.bypassFly.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cFly");
                            } else {
                                ArrayLists.bypassFly.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cFly");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9God")) {
                            if (!ArrayLists.bypassGodmode.contains(player)) {

                                ArrayLists.bypassGodmode.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cGodmode");
                            } else {
                                ArrayLists.bypassGodmode.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cGodmode");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Lockchat")) {
                            if (!ArrayLists.bypassLockchat.contains(player)) {

                                ArrayLists.bypassLockchat.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cLockchat");
                            } else {
                                ArrayLists.bypassLockchat.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cLockchat");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Vanish")) {
                            if (!ArrayLists.bypassVanish.contains(player)) {

                                ArrayLists.bypassVanish.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cVanish");
                            } else {
                                ArrayLists.bypassVanish.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cVanish");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Heal")) {
                            if (!ArrayLists.bypassHeal.contains(player)) {

                                ArrayLists.bypassHeal.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cHeal");
                            } else {
                                ArrayLists.bypassHeal.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cHeal");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Feed")) {
                            if (!ArrayLists.bypassFeed.contains(player)) {

                                ArrayLists.bypassFeed.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cFeed");
                            } else {
                                ArrayLists.bypassFeed.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cFeed");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Invsee")) {
                            if (!ArrayLists.bypassInvsee.contains(player)) {

                                ArrayLists.bypassInvsee.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cInvsee");
                            } else {
                                ArrayLists.bypassInvsee.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cInvsee");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§cAll")) {
                            if (!ArrayLists.bypassAll.contains(player)) {

                                ArrayLists.bypassAll.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing §cAll");
                            } else {
                                ArrayLists.bypassAll.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing §cAll");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§9Kill")) {
                            if (!ArrayLists.bypassAll.contains(player)) {
                                ArrayLists.bypassAll.add(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §2now §abypassing all Commands");
                            } else {
                                ArrayLists.bypassAll.remove(player);
                                player.sendMessage(AdminTools.getInstance().Prefix + "§aYou're §cno longer §abypassing all Commands");
                            }
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§2Next Page")) {
                            player.closeInventory();

                            player.sendTitle("§c§lComming Soon!", "");
                        } else if (evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lBack")) {
                            player.closeInventory();
                            ATCenterInv.AdminCommandsInv(player);
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lOnline players §7| §9Kill"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        Player playername = Bukkit.getPlayer(evt.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§9",""));
                        if(playername != null)
                        {
                            if(!(playername == player))
                            {
                                if(!ArrayLists.bypassKill.contains(playername))
                                {
                                    playername.setHealth(0);
                                    player.sendMessage(AdminTools.getInstance().Prefix + "§cThe player §a" + playername.getName() + "§c was killed");
                                    player.closeInventory();
                                }
                                else
                                {
                                    player.sendMessage(AdminTools.getInstance().Prefix + "§cThis player is bypassing kill");
                                    player.closeInventory();
                                }
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + "§cYou can't kill yourself!");
                                player.closeInventory();
                            }
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§cAdd player §7| §9Whitelist"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        String playername = evt.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§9", "");

                        if(!Bukkit.getServer().getWhitelistedPlayers().contains(playername))
                        {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "whitelist add " + playername);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aThe player §2" + playername + "§a was successfully added to the whitelist");
                            player.closeInventory();
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + "§cThis player is already whitelisted!");
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§cRemove player §7| §9Whitelist"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        String playername = evt.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§9", "");

                        if(Bukkit.getServer().getWhitelistedPlayers().contains(playername))
                        {
                            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "whitelist remove " + playername);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§aThe player §2" + playername + "§a was successfully removed to the whitelist");
                            player.closeInventory();
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + "§cThis player is not whitelisted");
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§cAdd player §7| §9Maintenance"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        String playername = evt.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§9", "");
                        File file = FileManager.getMaintenanceFile();
                        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                        if(!cfg.isSet("Players."+playername.toLowerCase()))
                        {
                            Bukkit.getServer().dispatchCommand(player, "maintenance add " + playername.toLowerCase());
                            player.closeInventory();
                        }
                        else
                        {
                            player.closeInventory();
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenanceAlreadySet.replace("%player%",playername.toLowerCase()));
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§cRem player §7| §9Maintenance"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        String playername = evt.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§9", "");
                        File file = FileManager.getMaintenanceFile();
                        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
                        if(cfg.isSet("Players."+playername.toLowerCase()))
                        {
                            Bukkit.getServer().dispatchCommand(player, "maintenance remove " + playername.toLowerCase());
                            player.closeInventory();
                        }
                        else
                        {
                            player.closeInventory();
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().MaintenancePlayerNotSet.replace("%player%",playername.toLowerCase()));
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /at");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§9Viewarmor"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /viewarmor");
                        player.closeInventory();
                    }

                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Center §7§l| §9Banlist"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        player.closeInventory();
                        Command_Checkban.openBanInfo(player, evt.getCurrentItem().getItemMeta().getDisplayName());
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /checkban");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Center §7§l| §9Mutelist"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        player.closeInventory();
                        Command_Checkmute.openMuteInfo(player, evt.getCurrentItem().getItemMeta().getDisplayName());
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /checkmute");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Center §7§l| §9Baninfo"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lClose"))
                        {
                            player.closeInventory();
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /checkban <player>");
                        player.closeInventory();
                    }
                }
            }
            else if(evt.getClickedInventory().getName().equals("§c§lAT Center §7§l| §9Muteinfo"))
            {
                if(evt.getCurrentItem() != null && evt.getCurrentItem().getItemMeta() != null)
                {
                    if(ArrayLists.inventoryIn.containsKey(player) && ArrayLists.inventoryIn.get(player).getTitle().contains(evt.getClickedInventory().getTitle()))
                    {
                        if(evt.getCurrentItem().getItemMeta().getDisplayName().equals("§c§lClose"))
                        {
                            player.closeInventory();
                        }
                        evt.setCancelled(true);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. Use /checkmute <player>");
                        player.closeInventory();
                    }
                }
            }
        }
    }
}
