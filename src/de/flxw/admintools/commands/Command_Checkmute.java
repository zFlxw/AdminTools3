package de.flxw.admintools.commands;

import de.flxw.admintools.ban.BanManager;
import de.flxw.admintools.mute.MuteManager;
import de.flxw.admintools.utils.AdminTools;
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

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Command_Checkmute implements CommandExecutor {
    public Command_Checkmute(AdminTools adminTools) {}
    private static final String PERM_CHECKMUTE = "admintools.checkmute";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_CHECKMUTE) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(AdminTools.getInstance().MySQLcon)
            {
                if(args.length == 0)
                {
                    List<String> list = MuteManager.getMutedPlayers();
                    if(list.size() == 0)
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cNo player muted!");
                    }
                    else
                    {
                        if(commandSender instanceof Player)
                        {
                            Player player = (Player) commandSender;
                            openMuteList(player);
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7--------------- [ §cMUTELIST §7] ---------------");
                            for (String allMuted : BanManager.getBannedPlayers())
                            {
                                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§e " + allMuted + " §7(§2Reason: §c" + BanManager.getReason(getUUID(allMuted)) + "§7)");
                            }
                        }
                    }
                }
                else if(args.length == 1)
                {
                    if(commandSender instanceof Player)
                    {
                        Player player = (Player) commandSender;
                        String playername = args[0];
                        openMuteInfo(player, playername);
                    }
                    else
                    {
                        String playername = args[0];
                        long endMillis = MuteManager.getEnd(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
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
                        commandSender.sendMessage(AdminTools.getInstance().MutePrefix + "Playername: §c" + playername);
                        commandSender.sendMessage(AdminTools.getInstance().MutePrefix + "Muted: §c" + (MuteManager.isMuted(getUUID(playername)) ? "Yes" : "No"));

                        if(MuteManager.isMuted(getUUID(playername))) {
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix + " Reason: §c" + MuteManager.getReason(getUUID(playername)));
                            commandSender.sendMessage(AdminTools.getInstance().MutePrefix + " Unmute Date: §c" + MuteManager.getUnbandate(getUUID(playername)));
                        }
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /checkmute (Player)");
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
    public static void openMuteList(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§c§lAT Center §7§l| §9Mutelist");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        for(String allMuted : MuteManager.getMutedPlayers())
        {
            OfflinePlayer muted = Bukkit.getOfflinePlayer(allMuted);
            String reason = MuteManager.getReason(muted.getUniqueId().toString());

            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
            SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
            itemMeta.setOwner(muted.getName());
            itemMeta.setDisplayName(muted.getName());
            itemMeta.setLore(Arrays.asList("§cReason: §9" + reason, "§aClick for more information"));
            item.setItemMeta(itemMeta);
            inventory.addItem(item);
        }
        player.openInventory(inventory);
    }
    public static void openMuteInfo(Player player, String playername)
    {
        if(MuteManager.isMuted(Bukkit.getOfflinePlayer(playername).getUniqueId().toString()))
        {
            String strReason = MuteManager.getReason(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
            Inventory inventory = Bukkit.createInventory(null, 9, "§c§lAT Center §7§l| §9Muteinfo");
            if(!ArrayLists.inventoryIn.containsKey(player))
            {
                ArrayLists.inventoryIn.put(player, inventory);
            }
            else
            {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
                return;
            }
            long endMillis = MuteManager.getEnd(Bukkit.getOfflinePlayer(playername).getUniqueId().toString());
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

            ItemStack unmute = new ItemStack(Material.WATCH);
            ItemMeta unmuteMeta = unmute.getItemMeta();
            unmuteMeta.setDisplayName("§cUnmute:");
            if(endDate.equals("PERMANENT"))
            {
                unmuteMeta.setLore(Arrays.asList("§8» §4" + endDate));
            }
            else
            {
                unmuteMeta.setLore(Arrays.asList("§8» §c" + endDate));
            }
            unmute.setItemMeta(unmuteMeta);

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
            inventory.setItem(4, unmute);
            inventory.setItem(5, placeholder);
            inventory.setItem(6, placeholder);
            inventory.setItem(8, close);

            player.openInventory(inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().MutePrefix + "Playername: §c" + playername);
            player.sendMessage(AdminTools.getInstance().MutePrefix + "Muted: §c" + (MuteManager.isMuted(getUUID(playername)) ? "Yes" : "No"));
        }
    }
    public static String getUUID(String playername)
    {
        return Bukkit.getOfflinePlayer(playername).getUniqueId().toString();
    }
}
