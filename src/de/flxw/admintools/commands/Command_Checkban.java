package de.flxw.admintools.commands;

import de.flxw.admintools.listener.InventoryListener;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.ban.BanManager;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Command_Checkban implements CommandExecutor {
    public Command_Checkban(AdminTools adminTools) {}

    private static final String PERM_CHECKBAN = "admintools.checkban";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender.hasPermission(PERM_CHECKBAN) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length == 0)
                {
                    List<String> list = BanManager.getBannedPlayers();
                    if(list.size() == 0)
                    {
                        commandSender.sendMessage(AdminTools.getInstance().BanPrefix + "§cNo player banned!");
                    }
                    else
                    {
                        if(commandSender instanceof Player)
                        {
                            Player player = (Player) commandSender;
                            openBanList(player);
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + "§7--------------- [ §cBANLIST §7] ---------------");
                            for (String allBanned : BanManager.getBannedPlayers())
                            {
                                commandSender.sendMessage(AdminTools.getInstance().BanPrefix + "§e " + allBanned + " §7(§2Reason: §c" + BanManager.getReason(getUUID(allBanned)) + "§7)");
                            }
                        }
                    }
                }
                else if (args.length == 1)
                {
                    if(commandSender instanceof Player)
                    {
                        Player player = (Player) commandSender;
                        String playername = args[0];
                        openBanInfo(player, playername);
                    }
                    else
                    {
                        String playername = args[0];
                        String strStaff = BanManager.getStaff(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
                        long endMillis = BanManager.getEnd(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
                        String endDate = "";
                        SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy HH:mm");
                        Calendar now = Calendar.getInstance();
                        sdf.setTimeZone(now.getTimeZone());
                        if(endMillis != -1)
                        {
                            Date result = new Date(endMillis);
                            endDate = sdf.format(result) + " (" + now.getTimeZone().getDisplayName() + ")";
                        }
                        else
                        {
                            endDate = "PERMANENT";
                        }
                        commandSender.sendMessage(AdminTools.getInstance().BanPrefix + "Playername: §c" + playername);
                        commandSender.sendMessage(AdminTools.getInstance().BanPrefix + "Banned: §c" + (BanManager.isBanned(getUUID(playername)) ? "Yes" : "No"));

                        if(BanManager.isBanned(getUUID(playername))) {
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + " Reason: §c" + BanManager.getReason(getUUID(playername)));
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + " Remaining Time: §c" + BanManager.getRemainingTime(getUUID(playername)));
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + " Unban: §c" + endDate);
                            commandSender.sendMessage(AdminTools.getInstance().BanPrefix + " Staff: §c" + strStaff);
                        }
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /checkban (Player)");
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cThis feature is currently disabled. Enable mysql in the config.yml file to reactivate it");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }

        return true;
    }

    public static void openBanList(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§c§lAT Center §7§l| §9Banlist");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }

        for(String allBanned : BanManager.getBannedPlayers())
        {
            OfflinePlayer banned = Bukkit.getOfflinePlayer(allBanned);
            String reason = BanManager.getReason(banned.getUniqueId().toString());

            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
            SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
            itemMeta.setOwner(banned.getName());
            itemMeta.setDisplayName(banned.getName());
            itemMeta.setLore(Arrays.asList("§cReason: §9" + reason, "§aClick for more information"));
            item.setItemMeta(itemMeta);
            inventory.addItem(item);
        }
        player.openInventory(inventory);
    }

    public static void openBanInfo(Player player, String playername)
    {
        if(BanManager.isBanned(Bukkit.getOfflinePlayer(playername).getUniqueId().toString()))
        {
            String strReason = BanManager.getReason(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
            String strStaff = BanManager.getStaff(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
            Inventory inventory = Bukkit.createInventory(null, 9, "§c§lAT Center §7§l| §9Baninfo");
            if(!ArrayLists.inventoryIn.containsKey(player))
            {
                ArrayLists.inventoryIn.put(player, inventory);
            }
            else
            {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
                return;
            }
            long endMillis = BanManager.getEnd(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
            String endDate = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy HH:mm");
            Calendar now = Calendar.getInstance();
            sdf.setTimeZone(now.getTimeZone());
            if(endMillis != -1)
            {
                Date result = new Date(endMillis);
                endDate = sdf.format(result) + " (" + now.getTimeZone().getDisplayName() + ")";
            }
            else
            {
                endDate = "PERMANENT";
            }
            ItemStack playerSkull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
            SkullMeta playerMeta = (SkullMeta) playerSkull.getItemMeta();
            playerMeta.setOwner(playername);
            playerMeta.setDisplayName("§cName:");
            playerMeta.setLore(Arrays.asList("§8» §9" + playername));
            playerSkull.setItemMeta(playerMeta);

            ItemStack reason = new ItemStack(Material.BOOK);
            ItemMeta reasonMeta = reason.getItemMeta();
            reasonMeta.setDisplayName("§cReason:");
            reasonMeta.setLore(Arrays.asList("§8» §9" + strReason));
            reason.setItemMeta(reasonMeta);

            ItemStack unban = new ItemStack(Material.WATCH);
            ItemMeta unbanMeta = unban.getItemMeta();
            unbanMeta.setDisplayName("§cUnban:");
            if(endDate.equals("PERMANENT"))
            {
                unbanMeta.setLore(Arrays.asList("§8» §4" + endDate));
            }
            else
            {
                unbanMeta.setLore(Arrays.asList("§8» §c" + endDate));
            }
            unban.setItemMeta(unbanMeta);

            ItemStack staff = new ItemStack(Material.IRON_AXE);
            ItemMeta staffMeta = staff.getItemMeta();
            staffMeta.setDisplayName("§cStaff:");
            staffMeta.setLore(Arrays.asList("§8» §9" + strStaff));
            staff.setItemMeta(staffMeta);

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName("§c§lClose");
            close.setItemMeta(closeMeta);

            ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
            ItemMeta placeholderMeta = placeholder.getItemMeta();
            placeholderMeta.setDisplayName(" ");
            placeholder.setItemMeta(placeholderMeta);

            inventory.setItem(0, playerSkull);
            inventory.setItem(1, reason);
            inventory.setItem(2, placeholder);
            inventory.setItem(3, placeholder);
            inventory.setItem(4, unban);
            inventory.setItem(5, placeholder);
            inventory.setItem(6, placeholder);
            inventory.setItem(7, staff);
            inventory.setItem(8, close);

            player.openInventory(inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().BanPrefix + "Playername: §c" + playername);
            player.sendMessage(AdminTools.getInstance().BanPrefix + "Banned: §c" + (BanManager.isBanned(getUUID(playername)) ? "Yes" : "No"));
        }
    }

    public static String getUUID(String playername)
    {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}
