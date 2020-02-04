package de.flxw.admintools.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;

public class ATCenterInv
{
    public static String PLUGIN_VERSION = AdminTools.getInstance().getDescription().getVersion();

    public static void AdminMainInv(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 9, "§4§lAT-Center §8§l| §9" + PLUGIN_VERSION);
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        ItemStack sCommands = new ItemStack(Material.PAPER);
        ItemMeta mCommands = sCommands.getItemMeta();
        mCommands.setDisplayName("§f§lCommands");
        sCommands.setItemMeta(mCommands);

        ItemStack sPlayerC = new ItemStack(Material.SKULL_ITEM);
        ItemMeta mPlayerC = sPlayerC.getItemMeta();
        mPlayerC.setDisplayName("§aPlayer Control");
        sPlayerC.setItemMeta(mPlayerC);

        ItemStack sServerC = new ItemStack(Material.REDSTONE);
        ItemMeta mServerC = sServerC.getItemMeta();
        mServerC.setDisplayName("§cServer Control");
        sServerC.setItemMeta(mServerC);

        ItemStack sSystems = new ItemStack(Material.IRON_SWORD);
        ItemMeta mSystems = sSystems.getItemMeta();
        mSystems.setDisplayName("§f§lKill");
        sSystems.setItemMeta(mSystems);

        ItemStack sWhitelist = new ItemStack(Material.SKULL_ITEM);
        ItemMeta mWhitelist = sWhitelist.getItemMeta();
        mWhitelist.setDisplayName("§9§lWhitelist");
        sWhitelist.setItemMeta(mWhitelist);

        ItemStack sPlayername = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
        SkullMeta mPlayername = (SkullMeta) sPlayername.getItemMeta();
        mPlayername.setOwner(player.getName());
        if(!ArrayLists.censor.contains(player))
        {
            mPlayername.setDisplayName("§aHello §b§l" + player.getDisplayName());
            mPlayername.setLore(Arrays.asList("§3Currently logged in as:", "§c" + player.getName()));
        }
        else
        {
            mPlayername.setDisplayName("§aHello §b§l" + "§k" + player.getDisplayName());
            mPlayername.setLore(Arrays.asList("§3Currently logged in as:", "§c§k" + player.getName()));
        }
        sPlayername.setItemMeta(mPlayername);

        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        ItemStack sClose = new ItemStack(Material.BARRIER);
        ItemMeta mClose = sClose.getItemMeta();
        mClose.setDisplayName("§c§lClose");
        sClose.setItemMeta(mClose);

        inventory.setItem(0, sCommands);
        inventory.setItem(1, placeholder);
        inventory.setItem(2, placeholder);
        inventory.setItem(3, sPlayerC);
        inventory.setItem(4, sPlayername);
        inventory.setItem(5, sServerC);
        inventory.setItem(6, placeholder);
        inventory.setItem(7, placeholder);
        inventory.setItem(8, sClose);

        player.openInventory(inventory);
    }
    public static void AdminCommandsInv(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 4*9, "§c§lAT Commands §7§l| §9Page 1");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        ItemStack sGamemode = new ItemStack(Material.PAPER);
        ItemMeta mGamemode = sGamemode.getItemMeta();
        mGamemode.setDisplayName("§c§lGamemode");
        sGamemode.setItemMeta(mGamemode);

        ItemStack sFly = new ItemStack(Material.PAPER);
        ItemMeta mFly = sFly.getItemMeta();
        mFly.setDisplayName("§c§lFly");
        sFly.setItemMeta(mFly);

        ItemStack sGod = new ItemStack(Material.PAPER);
        ItemMeta mGod = sGod.getItemMeta();
        mGod.setDisplayName("§c§lGodmode");
        sGod.setItemMeta(mGod);

        ItemStack sHeal = new ItemStack(Material.PAPER);
        ItemMeta mHeal = sHeal.getItemMeta();
        mHeal.setDisplayName("§c§lHeal");
        sHeal.setItemMeta(mHeal);

        ItemStack sFeed = new ItemStack(Material.PAPER);
        ItemMeta mFeed = sFeed.getItemMeta();
        mFeed.setDisplayName("§c§lFeed");
        sFeed.setItemMeta(mFeed);

        ItemStack sInvsee = new ItemStack(Material.PAPER);
        ItemMeta mInvsee = sInvsee.getItemMeta();
        mInvsee.setDisplayName("§c§lInvsee");
        sInvsee.setItemMeta(mInvsee);

        ItemStack sClearChat = new ItemStack(Material.PAPER);
        ItemMeta mClearChat = sClearChat.getItemMeta();
        mClearChat.setDisplayName("§c§lClearChat");
        sClearChat.setItemMeta(mClearChat);

        ItemStack sLockChat = new ItemStack(Material.PAPER);
        ItemMeta mLockChat = sLockChat.getItemMeta();
        mLockChat.setDisplayName("§c§lLockchat");
        sLockChat.setItemMeta(mLockChat);

        ItemStack sBypass = new ItemStack(Material.PAPER);
        ItemMeta mBypass = sBypass.getItemMeta();
        mBypass.setDisplayName("§c§lBypass");
        sBypass.setItemMeta(mBypass);

        ItemStack sCurrentpage = new ItemStack(Material.EMERALD);
        ItemMeta mCurrentpage = sCurrentpage.getItemMeta();
        mCurrentpage.setDisplayName("§aPage: §21");
        sCurrentpage.setItemMeta(mCurrentpage);

        ItemStack sPlaceholder = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte) 3);
        ItemMeta mPlaceholder = sPlaceholder.getItemMeta();
        mPlaceholder.setDisplayName(" ");
        sPlaceholder.setItemMeta(mPlaceholder);

        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        ItemStack sClose = new ItemStack(Material.BARRIER);
        ItemMeta mClose = sClose.getItemMeta();
        mClose.setDisplayName("§c§lBack");
        sClose.setItemMeta(mClose);

        inventory.setItem(0, sGamemode);
        inventory.setItem(1, placeholder);
        inventory.setItem(2, sFly);
        inventory.setItem(3, placeholder);
        inventory.setItem(4, sGod);
        inventory.setItem(5, placeholder);
        inventory.setItem(6, sHeal);
        inventory.setItem(7, placeholder);
        inventory.setItem(8, sFeed);
        inventory.setItem(9, placeholder);
        inventory.setItem(10, sInvsee);
        inventory.setItem(11, placeholder);
        inventory.setItem(12, sClearChat);
        inventory.setItem(13, placeholder);
        inventory.setItem(14, sLockChat);
        inventory.setItem(15, placeholder);
        inventory.setItem(16, sBypass);
        inventory.setItem(17, placeholder);
        inventory.setItem(18, placeholder);
        inventory.setItem(19, placeholder);
        inventory.setItem(20, placeholder);
        inventory.setItem(21, placeholder);
        inventory.setItem(22, placeholder);
        inventory.setItem(23, placeholder);
        inventory.setItem(24, placeholder);
        inventory.setItem(25, placeholder);
        inventory.setItem(26, placeholder);
        inventory.setItem(27, sPlaceholder);
        inventory.setItem(28, sPlaceholder);
        inventory.setItem(29, sPlaceholder);
        inventory.setItem(30, sPlaceholder);
        inventory.setItem(31, sCurrentpage);
        inventory.setItem(32, sPlaceholder);
        inventory.setItem(33, sPlaceholder);
        inventory.setItem(34, sPlaceholder);
        inventory.setItem(35, sClose);

        player.openInventory(inventory);
    }
    public static void AdminGamemodeInv(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 9, "§c§lAT Gamemode §7§l| §9" + PLUGIN_VERSION);
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        ItemStack sGM0 = new ItemStack(Material.STONE_SWORD);
        ItemMeta mGM0 = sGM0.getItemMeta();
        mGM0.setDisplayName("§9Survival");
        sGM0.setItemMeta(mGM0);

        ItemStack sGM1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta mGM1 = sGM1.getItemMeta();
        mGM1.setDisplayName("§9Creative");
        sGM1.setItemMeta(mGM1);

        ItemStack sGM2 = new ItemStack(Material.WOOD_SWORD);
        ItemMeta mGM2 = sGM2.getItemMeta();
        mGM2.setDisplayName("§9Adventure");
        sGM2.setItemMeta(mGM2);

        ItemStack sGM3 = new ItemStack(Material.IRON_SWORD);
        ItemMeta mGM3 = sGM3.getItemMeta();
        mGM3.setDisplayName("§9Spectator");
        sGM3.setItemMeta(mGM3);

        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        ItemStack sBack = new ItemStack(Material.BARRIER);
        ItemMeta mBack = sBack.getItemMeta();
        mBack.setDisplayName("§c§lBack");
        sBack.setItemMeta(mBack);

        inventory.setItem(0, placeholder);
        inventory.setItem(1, sGM0);
        inventory.setItem(2, placeholder);
        inventory.setItem(3, sGM1);
        inventory.setItem(4, placeholder);
        inventory.setItem(5, sGM2);
        inventory.setItem(6, placeholder);
        inventory.setItem(7, sGM3);
        inventory.setItem(8, sBack);

        player.openInventory(inventory);
    }
    public static void AdminInvseePlayersInv(Player player)
    {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
        Inventory playerInventory = Bukkit.createInventory(null, 9*5, "§cOnline players §7| §9Invsee");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, playerInventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        for(Player all : Bukkit.getOnlinePlayers())
        {
            if(Bukkit.getOnlinePlayers().size() < 54)
            {
                String n = all.getName();

                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

                skullMeta.setDisplayName("§9" + n);
                skullMeta.setOwner(n);
                skull.setItemMeta(skullMeta);

                playerInventory.addItem(skull);
            } else {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cCurrently too many players online (max. 54) :/ I'm working on a solution that there isn't a playerlimit");
                player.sendMessage(AdminTools.getInstance().Prefix + "§cUse: /invsee <Player>");
            }
        }
        player.openInventory(playerInventory);
    }
    public static void AdminBypassInv(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 4*9, "§c§lAT Bypass §7| §9Page 1");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        ItemStack sGm = new ItemStack(Material.PAPER);
        ItemMeta mGm = sGm.getItemMeta();
        mGm.setDisplayName("§9GameMode");
        sGm.setItemMeta(mGm);

        ItemStack sFly = new ItemStack(Material.PAPER);
        ItemMeta mFly = sFly.getItemMeta();
        mFly.setDisplayName("§9Fly");
        sFly.setItemMeta(mFly);

        ItemStack sGod = new ItemStack(Material.PAPER);
        ItemMeta mGod = sGod.getItemMeta();
        mGod.setDisplayName("§9God");
        sGod.setItemMeta(mGod);

        ItemStack sLockchat = new ItemStack(Material.PAPER);
        ItemMeta mLockchat = sLockchat.getItemMeta();
        mLockchat.setDisplayName("§9Lockchat");
        sLockchat.setItemMeta(mLockchat);

        ItemStack sVanish = new ItemStack(Material.PAPER);
        ItemMeta mVanish = sVanish.getItemMeta();
        mVanish.setDisplayName("§9Vanish");
        sVanish.setItemMeta(mVanish);

        ItemStack sHeal = new ItemStack(Material.PAPER);
        ItemMeta mHeal = sHeal.getItemMeta();
        mHeal.setDisplayName("§9Heal");
        sHeal.setItemMeta(mHeal);

        ItemStack sFeed = new ItemStack(Material.PAPER);
        ItemMeta mFeed = sFeed.getItemMeta();
        mFeed.setDisplayName("§9Feed");
        sFeed.setItemMeta(mFeed);

        ItemStack sInvsee = new ItemStack(Material.PAPER);
        ItemMeta mInvsee = sInvsee.getItemMeta();
        mInvsee.setDisplayName("§9Invsee");
        sInvsee.setItemMeta(mInvsee);

        ItemStack sKill = new ItemStack(Material.PAPER);
        ItemMeta mKill = sKill.getItemMeta();
        mKill.setDisplayName("§9Kill");
        sKill.setItemMeta(mKill);

        ItemStack sAll = new ItemStack(Material.PAPER);
        ItemMeta mAll = sAll.getItemMeta();
        mAll.setDisplayName("§cAll");
        sAll.setItemMeta(mAll);

        ItemStack sPage = new ItemStack(Material.BOOK);
        ItemMeta mPage = sPage.getItemMeta();
        mPage.setDisplayName("§2Next Page");
        sPage.setItemMeta(mPage);

        ItemStack censor = new ItemStack(Material.PAPER);
        ItemMeta censorMeta = censor.getItemMeta();
        censorMeta.setDisplayName("§9Censor");
        censor.setItemMeta(censorMeta);

        ItemStack freeze = new ItemStack(Material.PAPER);
        ItemMeta freezeMeta = freeze.getItemMeta();
        freezeMeta.setDisplayName("§9Freeze");
        freeze.setItemMeta(freezeMeta);

        ItemStack sBack = new ItemStack(Material.BARRIER);
        ItemMeta mBack = sBack.getItemMeta();
        mBack.setDisplayName("§c§lBack");
        sBack.setItemMeta(mBack);

        ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 8);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(" ");
        placeholder.setItemMeta(placeholderMeta);

        ItemStack sPlaceholder = new ItemStack(Material.STAINED_GLASS_PANE, 1,(byte) 3);
        ItemMeta mPlaceholder = sPlaceholder.getItemMeta();
        mPlaceholder.setDisplayName(" ");
        sPlaceholder.setItemMeta(mPlaceholder);

        ItemStack sCurrentpage = new ItemStack(Material.EMERALD);
        ItemMeta mCurrentpage = sCurrentpage.getItemMeta();
        mCurrentpage.setDisplayName("§aPage: §21");
        sCurrentpage.setItemMeta(mCurrentpage);

        for(int i = 0; i <36; i++)
        {
            inventory.setItem(i, placeholder);
        }
        inventory.setItem(0, sGm);
        inventory.setItem(2, sFly);
        inventory.setItem(4, sGod);
        inventory.setItem(6, sLockchat);
        inventory.setItem(8, sLockchat);
        inventory.setItem(10, sVanish);
        inventory.setItem(12, sHeal);
        inventory.setItem(14, sFeed);
        inventory.setItem(16, sInvsee);
        inventory.setItem(18, sKill);
        inventory.setItem(20, censor);
        inventory.setItem(22, freeze);
        inventory.setItem(27, sPlaceholder);
        inventory.setItem(28, sPlaceholder);
        inventory.setItem(29, sPlaceholder);
        inventory.setItem(30, sPlaceholder);
        inventory.setItem(31, sCurrentpage);
        inventory.setItem(32, sPlaceholder);
        inventory.setItem(33, sPlaceholder);
        inventory.setItem(34, sPage);
        inventory.setItem(35, sBack);

        player.openInventory(inventory);
    }

    public static void AdminPlayerControlInv(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 54 ,"§cAT Player Control §7| §9" + PLUGIN_VERSION);
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        ItemStack sKill = new ItemStack(Material.SKULL_ITEM);
        ItemMeta mKill = sKill.getItemMeta();
        mKill.setDisplayName("§cKill Player");
        sKill.setItemMeta(mKill);

        ItemStack sGm = new ItemStack(Material.DIAMOND);
        ItemMeta mGm = sGm.getItemMeta();
        mGm.setDisplayName("§cGamemode");
        mGm.setLore(Arrays.asList("§a(Gamesmodes are listed below)"));
        sGm.setItemMeta(mGm);

        ItemStack sGM0 = new ItemStack(Material.STONE_SWORD);
        ItemMeta mGM0 = sGM0.getItemMeta();
        mGM0.setDisplayName("§9Survival");
        sGM0.setItemMeta(mGM0);

        ItemStack sGM1 = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta mGM1 = sGM1.getItemMeta();
        mGM1.setDisplayName("§9Creative");
        sGM1.setItemMeta(mGM1);

        ItemStack sGM2 = new ItemStack(Material.WOOD_SWORD);
        ItemMeta mGM2 = sGM2.getItemMeta();
        mGM2.setDisplayName("§9Adventure");
        sGM2.setItemMeta(mGM2);

        ItemStack sGM3 = new ItemStack(Material.IRON_SWORD);
        ItemMeta mGM3 = sGM3.getItemMeta();
        mGM3.setDisplayName("§9Spectator");
        sGM3.setItemMeta(mGM3);

        ItemStack sBack = new ItemStack(Material.BARRIER);
        ItemMeta mBack = sBack.getItemMeta();
        mBack.setDisplayName("§c§lBack");
        sBack.setItemMeta(mBack);

        inventory.setItem(0, sGm);
        inventory.setItem(2, sKill);
        inventory.setItem(9, sGM0);
        inventory.setItem(18, sGM1);
        inventory.setItem(27, sGM2);
        inventory.setItem(36, sGM3);

        player.openInventory(inventory);

    }

    public static void AdminServerControlInv(Player player)
    {
        Inventory inventory = Bukkit.createInventory(null, 54 ,"§cAT Server Control §7| §9" + PLUGIN_VERSION);
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, inventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        ItemStack sWhitelist = new ItemStack(Material.BLAZE_POWDER);
        ItemMeta mWhitelist = sWhitelist.getItemMeta();
        mWhitelist.setDisplayName("§9Whitelist §a§lon §9/ §c§loff");
        sWhitelist.setItemMeta(mWhitelist);

        ItemStack sOn = new ItemStack(Material.STAINED_GLASS_PANE,1, (byte) 5);
        ItemMeta mOn = sOn.getItemMeta();
        mOn.setDisplayName("§a§lOn");
        sOn.setItemMeta(mOn);

        ItemStack sOff = new ItemStack(Material.STAINED_GLASS_PANE,1, (byte) 14);
        ItemMeta mOff = sOff.getItemMeta();
        mOff.setDisplayName("§c§lOff");
        sOff.setItemMeta(mOff);

        ItemStack sAdd = new ItemStack(Material.SKULL_ITEM);
        ItemMeta mAdd = sAdd.getItemMeta();
        mAdd.setDisplayName("§2Add Player");
        sAdd.setItemMeta(mAdd);

        ItemStack sRemove = new ItemStack(Material.SKULL_ITEM, 1, (byte) 1);
        ItemMeta mRemove = sRemove.getItemMeta();
        mRemove.setDisplayName("§cRemove Player");
        sRemove.setItemMeta(mRemove);

        ItemStack sList = new ItemStack(Material.BOOK);
        ItemMeta mList = sList.getItemMeta();
        mList.setDisplayName("§9Whitelisted Players");
        sList.setItemMeta(mList);

        ItemStack sBack = new ItemStack(Material.BARRIER);
        ItemMeta mBack = sBack.getItemMeta();
        mBack.setDisplayName("§c§lBack");
        sBack.setItemMeta(mBack);

        inventory.setItem(0, sWhitelist);
        inventory.setItem(1, sOn);
        inventory.setItem(2, sOff);
        inventory.setItem(4, sAdd);
        inventory.setItem(5, sRemove);
        inventory.setItem(8, sList);

        player.openInventory(inventory);

    }

    public static void AdminKillInv(Player player)
    {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
        Inventory playerInventory = Bukkit.createInventory(null, 9*5, "§c§lOnline players §7| §9Kill");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, playerInventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        for(Player all : Bukkit.getOnlinePlayers()) {

            if(Bukkit.getOnlinePlayers().size() < 54) {
                String n = all.getName();

                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

                skullMeta.setDisplayName("§9" + n);
                skullMeta.setOwner(n);
                skull.setItemMeta(skullMeta);

                playerInventory.addItem(skull);
            } else {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cCurrently too many players online (max. 54) :/ I'm working on a solution that there isn't a playerlimit");
            }
        }
        player.openInventory(playerInventory);
    }

    public static void AdminAddPlayerInv(Player player)
    {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
        Inventory playerInventory = Bukkit.createInventory(null, 9*5, "§cAdd player §7| §9Whitelist");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, playerInventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        for(Player all : Bukkit.getOnlinePlayers()) {

            if(Bukkit.getOnlinePlayers().size() <= 54) {
                String n = all.getName();

                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

                skullMeta.setDisplayName("§9" + n);
                skullMeta.setOwner(n);
                skull.setItemMeta(skullMeta);

                playerInventory.addItem(skull);
            } else {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cCurrently too many players online (max. 54) :/ I'm working on a solution that there isn't a playerlimit");
                player.sendMessage(AdminTools.getInstance().Prefix + "§cUse: /whitelist add <Player>");
            }
        }
        player.openInventory(playerInventory);
    }

    public static void AdminRemPlayerInv(Player player)
    {
        Player[] onlinePlayers = Bukkit.getOnlinePlayers().toArray(new Player[Bukkit.getOnlinePlayers().size()]);
        Inventory playerInventory = Bukkit.createInventory(null, 9*5, "§cRemove player §7| §9Whitelist");
        if(!ArrayLists.inventoryIn.containsKey(player))
        {
            ArrayLists.inventoryIn.put(player, playerInventory);
        }
        else
        {
            player.sendMessage(AdminTools.getInstance().Prefix + "§cUnverified access. You're already in an inventory. If you think this is an error, contact an server administrator");
            return;
        }
        for(Player all : Bukkit.getOnlinePlayers()) {

            if(Bukkit.getOnlinePlayers().size() <= 54) {
                String n = all.getName();

                ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
                SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

                skullMeta.setDisplayName("§9" + n);
                skullMeta.setOwner(n);
                skull.setItemMeta(skullMeta);

                playerInventory.addItem(skull);
            } else {
                player.sendMessage(AdminTools.getInstance().Prefix + "§cCurrently too many players online (max. 54) :/ I'm working on a solution that there isn't a playerlimit");
                player.sendMessage(AdminTools.getInstance().Prefix + "§cUse: /whitelist remove <Player>");
            }
        }
        player.openInventory(playerInventory);
    }
}
