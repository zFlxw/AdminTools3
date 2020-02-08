package de.flxw.admintools.utils;

import de.flxw.admintools.commands.*;
import de.flxw.admintools.listener.*;
import de.flxw.admintools.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminTools extends JavaPlugin
{
    public static AdminTools instance;
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
    public String ModtLine1;
    public String ModtLine2;
    public String UnknownCommand;
    public String PublicTime;
    public String MsgReplyError;
    public String AdminchatMessage;
    public String HatMessage;
    public String NoLongerHatMessage;
    public String OtherHatMessage;
    public String OtherNoLongerHatMessage;
    public String HatErrorMessage;
    public String PERM_ALL = "admintools.*";
    public int resourceId = 68455;
    public int GiveawayCounter;
    public int MaxPlayers;
    public int PingLightgreen;
    public int PingDarkgreen;
    public int PingLightred;
    public boolean DenyBuildWhileFreezed;
    public boolean Modt;
    public boolean UnknownCommandBoolean;
    public boolean LogIP;
    public boolean DisableUpdateMessage;
    public boolean MySQLcon;
    public String ToggleMsgMessage;
    public String OtherToggleMsgMessage;
    public String NoMsgMessage;

    @Override
    public void onEnable()
    {
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
    public void onDisable()
    {
        MySQL.disconnect();
    }
    public void registerEvents()
    {
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
    public void registerCommands()
    {
        getCommand("gm").setExecutor(new Command_Gamemode(this));
        getCommand("fly").setExecutor(new Command_Fly(this));
        getCommand("godmode").setExecutor(new Command_Godmode(this));
        getCommand("broadcast").setExecutor(new Command_Broadcast(this));
        getCommand("msg").setExecutor(new Command_MSG(this));
        getCommand("kick").setExecutor(new Command_Kick(this));
        getCommand("ban").setExecutor(new Command_Ban(this));
        getCommand("tempban").setExecutor(new Command_Tempban(this));
        getCommand("unban").setExecutor(new Command_Unban(this));
        getCommand("checkban").setExecutor(new Command_Checkban(this));
        getCommand("heal").setExecutor(new Command_Heal(this));
        getCommand("feed").setExecutor(new Command_Feed(this));
        getCommand("invsee").setExecutor(new Command_Invsee(this));
        getCommand("clearchat").setExecutor(new Command_ClearChat(this));
        getCommand("lockchat").setExecutor(new Command_Lockchat(this));
        getCommand("mute").setExecutor(new Command_Mute(this));
        getCommand("tempmute").setExecutor(new Command_Tempmute(this));
        getCommand("unmute").setExecutor(new Command_Unmute(this));
        getCommand("checkmute").setExecutor(new Command_Checkmute(this));
        getCommand("admintools").setExecutor(new Command_Admintools(this));
        getCommand("bypass").setExecutor(new Command_Bypass(this));
        getCommand("vanish").setExecutor(new Command_Vanish(this));
        getCommand("censor").setExecutor(new Command_Censor(this));
        getCommand("giveaway").setExecutor( new Command_Giveaway(this));
        getCommand("freeze").setExecutor(new Command_Freeze(this));
        getCommand("socialspy").setExecutor(new Command_Socialspy(this));
        getCommand("ping").setExecutor(new Command_Ping(this));
        getCommand("workbench").setExecutor(new Command_Workbench(this));
        getCommand("enderchest").setExecutor(new Command_Enderchest(this));
        getCommand("tphere").setExecutor(new Command_Tphere(this));
        getCommand("tpall").setExecutor(new Command_Tpall(this));
        getCommand("tpa").setExecutor(new Command_Tpa(this));
        getCommand("tpaccept").setExecutor(new Command_Tpaccept(this));
        getCommand("tpdeny").setExecutor(new Command_Tpdeny(this));
        getCommand("sign").setExecutor(new Command_Sign(this));
        getCommand("rename").setExecutor(new Command_Rename(this));
        getCommand("speed").setExecutor(new Command_Speed(this));
        getCommand("skull").setExecutor(new Command_Skull(this));
        getCommand("viewarmor").setExecutor(new Command_Viewarmor(this));
        getCommand("cls").setExecutor(new Command_Clearscreen(this));
        getCommand("playerinfo").setExecutor(new Command_Playerinfo(this));
        getCommand("adminchat").setExecutor(new Command_Adminchat(this));
        getCommand("day").setExecutor(new Command_Day(this));
        getCommand("night").setExecutor(new Command_Night(this));
        getCommand("togglemsg").setExecutor(new Command_ToggleMSG(this));
    }
    public void consoleMessage()
    {
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
    public static AdminTools getInstance() {return instance; }
}
