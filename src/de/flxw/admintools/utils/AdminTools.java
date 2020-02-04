package de.flxw.admintools.utils;

import de.flxw.admintools.commands.*;
import de.flxw.admintools.listener.*;
import de.flxw.admintools.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class AdminTools extends JavaPlugin
{
    public static AdminTools instance;
    public int resourceId = 68455;
    public String Prefix;
    public String BanPrefix;
    public String MutePrefix;
    public String NoPerm;
    public String NoPlayer;
    public String PlayerNotOnline;
    public String JoinMessage;
    public String JoinMessageTeam;
    public String QuitMessage;
    public String BroadcastPrefix;
    public String MsgPrefix;
    public String SocialspyPrefix;
    public String KickHeader;
    public String KickReason;
    public String KickedBy;
    public String BanHeader;
    public String BanReason;
    public String BannedBy;
    public String RemainingBan;
    public String BanAppeal;
    public String MuteMessage;
    public String DisallowMessage;
    public String GiveawayCount;
    public String GiveawayWinMessage;
    public String FreezeTitle1;
    public String FreezeTitle2;
    public boolean DenyBuildWhileFreezed;
    public String PingMessage;
    public String AlreadyBanned;
    public String Banned;
    public String BroadcastWarning;
    public String BypassMessage;
    public String IsBypassingMessage;
    public String AdminchatLogIn;
    public String AdminchatLogOff;
    public String AdminchatAlreadyLogOff;
    public String AdminchatAlreadyLogIn;
    public String NoBypassMessage;
    public String CensorMessage;
    public String NoCensorMessage;
    public String OtherCensorMessage;
    public String NoOtherCensorMessage;
    public String ClearChatMessage;
    public String EnderchestMessage;
    public String OtherEnderchestMessage;
    public String FeedMessage;
    public String OtherFeedMessage;
    public String FlyMessage;
    public String OtherFlyMessage;
    public String NoFlyMessage;
    public String OtherNoFlyMessage;
    public String FreezeMessage;
    public String NoFreezeMessage;
    public String OtherFreezeMessage;
    public String OtherNoFreezeMessage;
    public String GamemodeMessage;
    public String OtherGamemodeMessage;
    public int GiveawayCounter;
    public String GiveawayNotEnough;
    public String GiveawayStarted;
    public String GodmodeMessage;
    public String OtherGodmodeMessage;
    public String NoLongerGodmodeOther;
    public String NoGodmodeMessage;
    public String HealMessage;
    public String OtherHealMessage;
    public String AllHealMessage;
    public String AllFeedMessage;
    public String AllKicked;
    public String LockchatMessage;
    public String UnlockchatMessage;
    public String LockchatDenyMessage;
    public String AlreadyMuted;
    public String StaffMuteMessage;
    public int PingLightgreen;
    public int PingDarkgreen;
    public int PingLightred;
    public String RenameMessage;
    public String RenameValidItemMessage;
    public String RenameColorWarning;
    public String SignMessage;
    public String SignValidItemMessage;
    public String SignColorWarning;
    public String SkullName;
    public String SkullMessage;
    public String OtherSkullMessage;
    public String SocialSpyMessage;
    public String DisableSocialSpyMessage;
    public String OtherSocialSpyMessage;
    public String OtherDisableSocialSpyMessage;
    public String SpeedMessage;
    public String OtherSpeedMessage;
    public String SpeedValidMessage;
    public String PTimeMessage;
    public String TpaIncommingMessage;
    public String TpaSenderMessage;
    public String TpallMessage;
    public String OtherTpallMessage;
    public String TpdenyMessage;
    public String OtherTpdenyMessage;
    public String TpacceptMessage;
    public String OtherTpacceptMessage;
    public String TphereMessage;
    public String OtherTphereMessage;
    public String UnbanMessage;
    public String IsNotBannedMessage;
    public String IsNotMutedMessage;
    public String UnmuteMessage;
    public String VanishMessage;
    public String NoLongerVanishMessage;
    public String OtherVanish;
    public String OtherNoLongerVanish;
    public String PWeatherMessage;
    public String WorkbenchMessage;
    public String OtherWorkbenchMessage;
    public String Antiban;
    public String Antimute;
    public boolean Modt;
    public String ModtLine1;
    public String ModtLine2;
    public String UnknownCommand;
    public String PublicTime;
    public int MaxPlayers;
    public boolean UnknownCommandBoolean;
    public boolean LogIP;
    public String MsgReplyError;
    public String AdminchatMessage;
    public String HatMessage;
    public String NoLongerHatMessage;
    public String OtherHatMessage;
    public String OtherNoLongerHatMessage;
    public String HatErrorMessage;
    public boolean DisableUpdateMessage;
    public String PERM_ALL = "admintools.*";
    public boolean MySQLcon;

    @Override
    public void onEnable() {
        instance = this;
        FileManager.setStandardConfig();
        FileManager.setMySQL();
        FileManager.readConfig();
        FileManager.readMySQL();
        if(MySQLcon)
        {
            MySQL.connect();
            MySQL.createTable();
        }
        else
        {
            Bukkit.getConsoleSender().sendMessage(Prefix + "§cMySQL is currently disabled. You can change this in the config.yml file");
        }
        registerCommands();
        registerEvents();
        consoleMessage();
    }

    @Override
    public void onDisable() {
        MySQL.closeConnection();
    }

    public void registerEvents() {
        PluginManager pm = this.getServer().getPluginManager();

        pm.registerEvents(new JoinListener(this), this);
        pm.registerEvents(new DamageListener(this), this);
        pm.registerEvents(new InventoryListener(this), this);
        pm.registerEvents(new PlayerLoginListener(this), this);
        pm.registerEvents(new ChatListener(this), this);
        pm.registerEvents(new Events(this), this);
        pm.registerEvents(new SignEvents(this), this);
        pm.registerEvents(new ServerListPingEvent(this), this);
        pm.registerEvents(new CommandPreprocessListener(this), this);
        pm.registerEvents(new PlayerCloseInventoryListener(this), this);
    }

    public void registerCommands() {

        Command_Gamemode cGM = new Command_Gamemode(this);
        getCommand("gm").setExecutor(cGM);

        Command_Fly cFly = new Command_Fly(this);
        getCommand("fly").setExecutor(cFly);

        Command_Godmode cGod = new Command_Godmode(this);
        getCommand("godmode").setExecutor(cGod);

        Command_Broadcast cBroadcast = new Command_Broadcast(this);
        getCommand("broadcast").setExecutor(cBroadcast);

        Command_MSG cMSG = new Command_MSG(this);
        getCommand("msg").setExecutor(cMSG);

        Command_Kick cKick = new Command_Kick(this);
        getCommand("kick").setExecutor(cKick);

        Command_Ban cBan = new Command_Ban(this);
        getCommand("ban").setExecutor(cBan);

        Command_Tempban cTempban = new Command_Tempban(this);
        getCommand("tempban").setExecutor(cTempban);

        Command_Unban cUnban = new Command_Unban(this);
        getCommand("unban").setExecutor(cUnban);

        Command_Checkban cCheckban = new Command_Checkban(this);
        getCommand("checkban").setExecutor(cCheckban);

        Command_Heal cHeal = new Command_Heal(this);
        getCommand("heal").setExecutor(cHeal);

        Command_Feed cFeed = new Command_Feed(this);
        getCommand("feed").setExecutor(cFeed);

        Command_Invsee cInvsee = new Command_Invsee(this);
        getCommand("invsee").setExecutor(cInvsee);

        Command_ClearChat cClearChat = new Command_ClearChat(this);
        getCommand("clearchat").setExecutor(cClearChat);

        Command_Lockchat cLockchat = new Command_Lockchat(this);
        getCommand("lockchat").setExecutor(cLockchat);

        Command_Mute cMute = new Command_Mute(this);
        getCommand("mute").setExecutor(cMute);

        Command_Tempmute cTempmute = new Command_Tempmute(this);
        getCommand("tempmute").setExecutor(cTempmute);

        Command_Unmute cUnmute = new Command_Unmute(this);
        getCommand("unmute").setExecutor(cUnmute);

        Command_Checkmute cCheckMute = new Command_Checkmute(this);
        getCommand("checkmute").setExecutor(cCheckMute);

        Command_Admintools cAdmintools = new Command_Admintools(this);
        getCommand("admintools").setExecutor(cAdmintools);

        Command_Bypass cBypass = new Command_Bypass(this);
        getCommand("bypass").setExecutor(cBypass);

        Command_Vanish cVanish = new Command_Vanish(this);
        getCommand("vanish").setExecutor(cVanish);

        Command_Censor cCensor = new Command_Censor(this);
        getCommand("censor").setExecutor(cCensor);

        Command_Giveaway cGiveaway = new Command_Giveaway(this);
        getCommand("giveaway").setExecutor(cGiveaway);

        Command_Freeze cFreeze = new Command_Freeze(this);
        getCommand("freeze").setExecutor(cFreeze);

        Command_Socialspy cSocialspy = new Command_Socialspy(this);
        getCommand("socialspy").setExecutor(cSocialspy);

        Command_Ping cPing = new Command_Ping(this);
        getCommand("ping").setExecutor(cPing);

        Command_Workbench cWorkbench = new Command_Workbench(this);
        getCommand("workbench").setExecutor(cWorkbench);

        Command_Enderchest cEnderchest = new Command_Enderchest(this);
        getCommand("enderchest").setExecutor(cEnderchest);

        Command_Tphere cTphere = new Command_Tphere(this);
        getCommand("tphere").setExecutor(cTphere);

        Command_Tpall cTpall = new Command_Tpall(this);
        getCommand("tpall").setExecutor(cTpall);

        Command_Tpa cTpa = new Command_Tpa(this);
        getCommand("tpa").setExecutor(cTpa);

        Command_Tpaccept cTpaccept = new Command_Tpaccept(this);
        getCommand("tpaccept").setExecutor(cTpaccept);

        Command_Tpdeny cTpdeny = new Command_Tpdeny(this);
        getCommand("tpdeny").setExecutor(cTpdeny);

        Command_Sign cSign = new Command_Sign(this);
        getCommand("sign").setExecutor(cSign);

        Command_Rename cRename = new Command_Rename(this);
        getCommand("rename").setExecutor(cRename);

        Command_Speed cSpeed = new Command_Speed(this);
        getCommand("speed").setExecutor(cSpeed);

        Command_Skull cSkull = new Command_Skull(this);
        getCommand("skull").setExecutor(cSkull);

        Command_Viewarmor cArmor = new Command_Viewarmor(this);
        getCommand("viewarmor").setExecutor(cArmor);

        Command_Clearscreen cCLS = new Command_Clearscreen(this);
        getCommand("cls").setExecutor(cCLS);

        Command_Playerinfo cPlayerinfo = new Command_Playerinfo(this);
        getCommand("playerinfo").setExecutor(cPlayerinfo);

        Command_Adminchat cAdminchat = new Command_Adminchat(this);
        getCommand("adminchat").setExecutor(cAdminchat);

        Command_Day cDay = new Command_Day(this);
        getCommand("day").setExecutor(cDay);

        Command_Night cNight = new Command_Night(this);
        getCommand("night").setExecutor(cNight);

        Command_Hat cHat = new Command_Hat(this);
        getCommand("hat").setExecutor(cHat);

    }

    public void consoleMessage() {
        Bukkit.getConsoleSender().sendMessage("§7 ---------------> §cAdminTools §7<----------");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage(">> §bVersion: §e" + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage(">> §bDeveloper: §ezFlxw");
        Bukkit.getConsoleSender().sendMessage(">> §aThank you for downloading my Plugin!");
        Bukkit.getConsoleSender().sendMessage(">> §aIf you have any suggestions, make sure to post them at spigotmc.org! :)");
        Bukkit.getConsoleSender().sendMessage(" ");
        new UpdateChecker(this, resourceId).getVersion(version -> {
            if(!this.getDescription().getVersion().equalsIgnoreCase(version))
            {
                Bukkit.getConsoleSender().sendMessage(Prefix + ">> §6§la newer version is available. Download on spigotmc.org!");
                Bukkit.getConsoleSender().sendMessage(Prefix + ">> §6§lgladly you can also rate my plugin on spigotmc :)");
            }
        });
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§7 ---------------> §cAdminTools §7<----------");
    }

    public static AdminTools getInstance() {
        return instance;
    }

}
