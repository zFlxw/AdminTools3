package de.flxw.admintools.utils;

import de.flxw.admintools.mysql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class FileManager {

    public static File getConfigFile() {return new File("plugins/AdminTools v3", "config.yml");}
    public static FileConfiguration getConfigFileConfiguration() {return YamlConfiguration.loadConfiguration(getConfigFile());}
    public static File getMaintenanceFile() {return new File("plugins/AdminTools v3", "maintenance.yml");}
    public static FileConfiguration getMaintenanceFileConfig() {return YamlConfiguration.loadConfiguration(getMaintenanceFile());}
    public static File getMySQL() {return new File("plugins/AdminTools v3", "mysql.yml");}
    public static FileConfiguration getMySQLConfig() {return YamlConfiguration.loadConfiguration(getMySQL());}

    public static void setStandardConfig()
    {
        if(!getConfigFile().exists())
        {
            new FileCopy().copy(AdminTools.getInstance().getResource("config.yml"), getConfigFile());
        }
    }
    public static void readConfig()
    {
        FileConfiguration cfg = getConfigFileConfiguration();
        try {
            String prefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefixes.mainPrefix"));
            String muteprefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefixes.mutePrefix"));
            String banprefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefixes.banPrefix"));
            String noperm = ChatColor.translateAlternateColorCodes('&', cfg.getString("ServerMessages.nopermission"));
            String noplayer = ChatColor.translateAlternateColorCodes('&', cfg.getString("ServerMessages.noplayer"));
            String playernull = ChatColor.translateAlternateColorCodes('&', cfg.getString("ServerMessages.playerNotOnline"));
            String joinMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ServerMessages.joinMessageNormal"));
            String joinMessageTeam = ChatColor.translateAlternateColorCodes('&', cfg.getString("ServerMessages.joinMessageTeam"));
            String quitMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ServerMessages.quitMessage"));
            String broadcastPrefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.broadcastPrefix"));
            String broadcastWarning = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.broadcastColorcodesWarning"));
            String msgPrefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefixes.msgPrefix"));
            String spyPrefix = ChatColor.translateAlternateColorCodes('&', cfg.getString("Prefixes.socialspyPrefix"));
            String ping = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.pingMessage"));
            String bypass = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.bypassMessage"));
            String nlbypass = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerBypassMessage"));
            String censorMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.censorMessage"));
            String nocensorMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerCensorMessage"));
            String othercensorMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherCensorMessage"));
            String othernolongercensor = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherNoLongerCensorMessage"));
            String chatclear = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.clearChatMessage"));
            String isbypassing = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.isBypassingMessage"));
            String enderchest = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.enderchestOpenMessage"));
            String otherenderchest = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherEnderchestOpenMessage"));
            String feedmessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.feedMessage"));
            String otherfeedmessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherFeedMessage"));
            String allfeed = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.allPlayerFeedMessage"));
            String flymessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.flyMessage"));
            String otherflymessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.flyOtherMessage"));
            String nolongerfly = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerFlyMessage"));
            String gamemode = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.gamemodeMessage"));
            String othergamemode = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherGamemodeMessage"));
            String othernolongerfly = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerFlyOtherMessage"));
            String freeze = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.freezeMessage"));
            String nofreeze = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerFreezeMessage"));
            String otherfreeze = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherFreezeMessage"));
            String othernofreeze = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherNoLongerFreezeMessage"));
            String kickHeader = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickConfig.kickHeader"));
            String kickReason = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickConfig.kickReason"));
            String kickedBy = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickConfig.kickedBy"));
            String banHeader = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.banHeader"));
            String banReason = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.banReason"));
            String bannedBy = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.bannedBy"));
            String banRemaining = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.remainingBanTime"));
            String banAppeal = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.banAppeal"));
            String alreadyBanned = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.alreadyBanned"));
            String banned = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.banned"));
            String muteMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.mute"));
            String disallowMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.disallowMessage"));
            String giveawayCount = ChatColor.translateAlternateColorCodes('&', cfg.getString("GiveawayConfig.giveawayCountdown"));
            String givewayWinMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("GiveawayConfig.giveawayWinnerMessage"));
            String freezeTitle1 = ChatColor.translateAlternateColorCodes('&', cfg.getString("FreezeConfig.freezeTitle1"));
            String freezeTitle2 = ChatColor.translateAlternateColorCodes('&', cfg.getString("FreezeConfig.freezeTitle2"));
            String giveawaynot = ChatColor.translateAlternateColorCodes('&', cfg.getString("GiveawayConfig.giveawayNotEnoughPlayers"));
            String giveawaystart = ChatColor.translateAlternateColorCodes('&', cfg.getString("GiveawayConfig.giveawayStarted"));
            String godmode = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.godmodeMessage"));
            String noLongerGodmode = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerGodmodeMessage"));
            String othergodmode = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherGodmodeMessage"));
            String noLongerOthergodmode = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerOtherGodmodeMessage"));
            String heal = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.healMessage"));
            String otherheal = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherHealMessage"));
            String allheal = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.allPlayerHealMessage"));
            String allkicked = ChatColor.translateAlternateColorCodes('&', cfg.getString("KickConfig.allKickedMessage"));
            String lockchat = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.lockchatMessage"));
            String unlockchat = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.unlockchatMessage"));
            String lockchatdeny = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.lockchatDenyMessage"));
            String alreadyMuted = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.alreadyMuted"));
            String staffMute = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.staffMessage"));
            String rename = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.renameMessage"));
            String renamevalid = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.renameValidItem"));
            String renamecolorwarning = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.renameColorcodesWarning"));
            String sign = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.signMessage"));
            String signvalid = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.signInvalidItem"));
            String signcolorwarning = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.signColorcodesWarning"));
            String skullname = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.SkullName"));
            String skullmessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.skullMessage"));
            String otherskullmessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherSkullMessage"));
            String socialspy = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.socialspyEnableMessage"));
            String disablesocialspy = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.socialspyDisableMessage"));
            String othersocialspy = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherSocialspyEnableMessage"));
            String otherdisablesocialspy = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherSocialspyDisableMessage"));
            String speed = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.speedMessage"));
            String otherspeed = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherSpeedMessage"));
            String speedvalid = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.speedInvalidMessage"));
            String time = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.personalTimeMessage"));
            String tpaincomming = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.tpaIncommingMessage"));
            String tpasender = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.tpaSenderMessage"));
            String tpall = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.tpallMessage"));
            String othertpall = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherTpallMessage"));
            String tpdeny = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.tpdenyMessage"));
            String othertpdeny = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherTpdenyMessage"));
            String tpaccept = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.tpacceptMessage"));
            String othertpaccept = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherTpacceptMessage"));
            String tphere = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.tphereMessage"));
            String othertphere = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherTphereMessage"));
            String unban = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.unbanMessage"));
            String isnotbanned = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.isNotBannedMessage"));
            String isnotmuted = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.isNotMuted"));
            String unmute = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.unmuteMessage"));
            String vanish = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.vanishMessage"));
            String novanish = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noLongerVanishMessage"));
            String othervanish = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherVanishMessage"));
            String othernovanish = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherNoLongerVanishMessage"));
            String weather = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.personalWeatherMessage"));
            String workbench = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.workbenchMessage"));
            String otherworkbench = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherWorkbenchMessage"));
            String adminchatlogin = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminchatLogInMessage"));
            String adminchatlogoff = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminchatLogOffMessage"));
            String adminchatalreadylogon = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminchatAlreadyLogInMessage"));
            String adminchatalreadylogoff = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminchatAlreadyLogOffMessage"));
            String antiban = ChatColor.translateAlternateColorCodes('&', cfg.getString("BanConfig.cannotBanMessage"));
            String antimute = ChatColor.translateAlternateColorCodes('&', cfg.getString("MuteMessages.cannotMuteMessage"));
            String modtline1 = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.modtLine1"));
            String modtline2 = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.modtLine2"));
            String unknowncommand = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.unknownCommandMessage"));
            String publictime = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.timeMessage"));
            String msgerror = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.msgReplyToYourselfMessage"));
            String adminchat = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminchatRespondMessage"));
            String toggleMsgMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.toggleMsgMessage"));
            String otherToggleMsgMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.otherToggleMsgMessage"));
            String noMsgMessage = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.noMsgMessage"));
            String statusMaintenance = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceStatusMessage"));
            String nomaintenancemode = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceNoModeSetMessage"));
            String maintenanceon = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceOnMessage"));
            String maintenanceoff = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceOffMessage"));
            String maintenancealreadyon = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceAlreadyOnMessage"));
            String maintenancealreadyoff = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceAlreadyOffMessage"));
            String maintenanceadd = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenancePlayerAddMessage"));
            String maintenanceremove = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenancePlayerRemoveMessage"));
            String maintenanceplayernotset = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenancePlayerNotSetMessage"));
            String maintenancealreadyset = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenancePlayerAlreadySetMessage"));
            String maintenanceMotdLine1 = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.mainenanceMotdLine1"));
            String maintenanceMotdLine2 = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.mainenanceMotdLine2"));
            String maintenanceKickHeader = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceKickHeader"));
            String maintenanceKickLine1 = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceKickLine1"));
            String maintenanceKickLine2 = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceKickLine2"));
            String maintenanceKickLine3 = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceKickLine3"));
            String maintenanceKickFooter = ChatColor.translateAlternateColorCodes('&', cfg.getString("MaintenanceConfig.maintenanceKickFooter"));
            String admintoolsitemname = ChatColor.translateAlternateColorCodes('&', cfg.getString("Settings.admintoolsItemName"));
            String admintoolsitemadd = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminitemAdded"));
            String admintoolsitemalreadyin = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.adminitemAlreadyInInventory"));
            String currentworldclear = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.entityCurrentWorldClearMessage"));
            String allworldclear = ChatColor.translateAlternateColorCodes('&', cfg.getString("ChatMessages.entityAllWorldClearMessage"));
            boolean modt = cfg.getBoolean("Settings.modt");
            boolean unknown = cfg.getBoolean("Settings.unknownCommand");
            boolean mysql = cfg.getBoolean("mysql");
            boolean denyBuildWhileFreezed = cfg.getBoolean("FreezeConfig.denyBuildWhileFreezed");
            boolean logip = cfg.getBoolean("Settings.logIpAddress");
            boolean updatemessage = cfg.getBoolean("Settings.disableUpdateMessageOnJoin");
            boolean maintenance = cfg.getBoolean("activateMaintenance");
            boolean getitemonjoin = cfg.getBoolean("Settings.getAdmintoolsItemOnJoin");
            int giveawaycount = cfg.getInt("GiveawayConfig.giveawaySeconds");
            int pingLightgreen = cfg.getInt("Settings.pingLightgreen");
            int pingDarkgreen = cfg.getInt("Settings.pingDarkgreen");
            int pingLightred = cfg.getInt("Settings.pingLightred");
            int maxplayers = cfg.getInt("Settings.maxPlayers");
            AdminTools.getInstance().Prefix = prefix;
            AdminTools.getInstance().BanPrefix = banprefix;
            AdminTools.getInstance().MutePrefix = muteprefix;
            AdminTools.getInstance().NoPerm = noperm;
            AdminTools.getInstance().NoPlayer = noplayer;
            AdminTools.getInstance().PlayerNotOnline = playernull;
            AdminTools.getInstance().JoinMessage = joinMessage;
            AdminTools.getInstance().JoinMessageTeam = joinMessageTeam;
            AdminTools.getInstance().BroadcastPrefix = broadcastPrefix;
            AdminTools.getInstance().BroadcastWarning = broadcastWarning;
            AdminTools.getInstance().MsgPrefix = msgPrefix;
            AdminTools.getInstance().SocialspyPrefix = spyPrefix;
            AdminTools.getInstance().BypassMessage = bypass;
            AdminTools.getInstance().IsBypassingMessage = isbypassing;
            AdminTools.getInstance().NoBypassMessage = nlbypass;
            AdminTools.getInstance().KickHeader = kickHeader;
            AdminTools.getInstance().KickReason = kickReason;
            AdminTools.getInstance().KickedBy = kickedBy;
            AdminTools.getInstance().BanHeader = banHeader;
            AdminTools.getInstance().BanReason = banReason;
            AdminTools.getInstance().BannedBy = bannedBy;
            AdminTools.getInstance().RemainingBan = banRemaining;
            AdminTools.getInstance().BanAppeal = banAppeal;
            AdminTools.getInstance().MuteMessage = muteMessage;
            AdminTools.getInstance().DisallowMessage = disallowMessage;
            AdminTools.getInstance().QuitMessage = quitMessage;
            AdminTools.getInstance().GiveawayCount = giveawayCount;
            AdminTools.getInstance().GiveawayWinMessage = givewayWinMessage;
            AdminTools.getInstance().FreezeTitle1 = freezeTitle1;
            AdminTools.getInstance().FreezeTitle2 = freezeTitle2;
            AdminTools.getInstance().DenyBuildWhileFreezed = denyBuildWhileFreezed;
            AdminTools.getInstance().PingMessage = ping;
            AdminTools.getInstance().AlreadyBanned = alreadyBanned;
            AdminTools.getInstance().CensorMessage = censorMessage;
            AdminTools.getInstance().NoCensorMessage = nocensorMessage;
            AdminTools.getInstance().ClearChatMessage = chatclear;
            AdminTools.getInstance().OtherCensorMessage = othercensorMessage;
            AdminTools.getInstance().NoOtherCensorMessage = othernolongercensor;
            AdminTools.getInstance().EnderchestMessage = enderchest;
            AdminTools.getInstance().OtherEnderchestMessage = otherenderchest;
            AdminTools.getInstance().FeedMessage = feedmessage;
            AdminTools.getInstance().OtherFeedMessage = otherfeedmessage;
            AdminTools.getInstance().FlyMessage = flymessage;
            AdminTools.getInstance().OtherFlyMessage = otherflymessage;
            AdminTools.getInstance().NoFlyMessage = nolongerfly;
            AdminTools.getInstance().OtherNoFlyMessage = othernolongerfly;
            AdminTools.getInstance().FreezeMessage = freeze;
            AdminTools.getInstance().NoFreezeMessage = nofreeze;
            AdminTools.getInstance().OtherNoFreezeMessage = othernofreeze;
            AdminTools.getInstance().OtherFreezeMessage = otherfreeze;
            AdminTools.getInstance().GamemodeMessage = gamemode;
            AdminTools.getInstance().OtherGamemodeMessage = othergamemode;
            AdminTools.getInstance().GiveawayCounter = giveawaycount;
            AdminTools.getInstance().GiveawayNotEnough = giveawaynot;
            AdminTools.getInstance().GiveawayStarted = giveawaystart;
            AdminTools.getInstance().GodmodeMessage = godmode;
            AdminTools.getInstance().OtherGodmodeMessage = othergodmode;
            AdminTools.getInstance().NoLongerGodmodeOther = noLongerOthergodmode;
            AdminTools.getInstance().NoGodmodeMessage = noLongerGodmode;
            AdminTools.getInstance().HealMessage = heal;
            AdminTools.getInstance().OtherHealMessage = otherheal;
            AdminTools.getInstance().AllHealMessage = allheal;
            AdminTools.getInstance().AllFeedMessage = allfeed;
            AdminTools.getInstance().AllKicked = allkicked;
            AdminTools.getInstance().LockchatMessage = lockchat;
            AdminTools.getInstance().UnlockchatMessage = unlockchat;
            AdminTools.getInstance().LockchatDenyMessage = lockchatdeny;
            AdminTools.getInstance().AlreadyMuted = alreadyMuted;
            AdminTools.getInstance().StaffMuteMessage = staffMute;
            AdminTools.getInstance().PingLightgreen = pingLightgreen;
            AdminTools.getInstance().PingDarkgreen = pingDarkgreen;
            AdminTools.getInstance().PingLightred = pingLightred;
            AdminTools.getInstance().RenameMessage = rename;
            AdminTools.getInstance().RenameValidItemMessage = renamevalid;
            AdminTools.getInstance().RenameColorWarning = renamecolorwarning;
            AdminTools.getInstance().SignMessage = sign;
            AdminTools.getInstance().SignValidItemMessage = signvalid;
            AdminTools.getInstance().SignColorWarning = signcolorwarning;
            AdminTools.getInstance().SkullName = skullname;
            AdminTools.getInstance().SkullMessage = skullmessage;
            AdminTools.getInstance().OtherSkullMessage = otherskullmessage;
            AdminTools.getInstance().SocialSpyMessage = socialspy;
            AdminTools.getInstance().OtherSocialSpyMessage = othersocialspy;
            AdminTools.getInstance().DisableSocialSpyMessage = disablesocialspy;
            AdminTools.getInstance().OtherDisableSocialSpyMessage = otherdisablesocialspy;
            AdminTools.getInstance().SpeedMessage = speed;
            AdminTools.getInstance().OtherSpeedMessage = otherspeed;
            AdminTools.getInstance().SpeedValidMessage = speedvalid;
            AdminTools.getInstance().PTimeMessage = time;
            AdminTools.getInstance().TpaSenderMessage = tpasender;
            AdminTools.getInstance().TpaIncommingMessage = tpaincomming;
            AdminTools.getInstance().TpallMessage = tpall;
            AdminTools.getInstance().OtherTpallMessage = othertpall;
            AdminTools.getInstance().TpdenyMessage = tpdeny;
            AdminTools.getInstance().OtherTpdenyMessage = othertpdeny;
            AdminTools.getInstance().TpacceptMessage = tpaccept;
            AdminTools.getInstance().OtherTpacceptMessage = othertpaccept;
            AdminTools.getInstance().TphereMessage = tphere;
            AdminTools.getInstance().OtherTphereMessage = othertphere;
            AdminTools.getInstance().UnbanMessage = unban;
            AdminTools.getInstance().IsNotBannedMessage = isnotbanned;
            AdminTools.getInstance().IsNotMutedMessage = isnotmuted;
            AdminTools.getInstance().UnmuteMessage = unmute;
            AdminTools.getInstance().VanishMessage = vanish;
            AdminTools.getInstance().NoLongerVanishMessage = novanish;
            AdminTools.getInstance().OtherVanish = othervanish;
            AdminTools.getInstance().OtherNoLongerVanish = othernovanish;
            AdminTools.getInstance().PWeatherMessage = weather;
            AdminTools.getInstance().OtherWorkbenchMessage = otherworkbench;
            AdminTools.getInstance().WorkbenchMessage = workbench;
            AdminTools.getInstance().Antiban = antiban;
            AdminTools.getInstance().Antimute = antimute;
            AdminTools.getInstance().Modt = modt;
            AdminTools.getInstance().ModtLine1 = modtline1;
            AdminTools.getInstance().ModtLine2 = modtline2;
            AdminTools.getInstance().MaxPlayers = maxplayers;
            AdminTools.getInstance().UnknownCommandBoolean = unknown;
            AdminTools.getInstance().UnknownCommand = unknowncommand;
            AdminTools.getInstance().PublicTime = publictime;
            AdminTools.getInstance().LogIP = logip;
            AdminTools.getInstance().MsgReplyError = msgerror;
            AdminTools.getInstance().AdminchatMessage = adminchat;
            AdminTools.getInstance().DisableUpdateMessage = updatemessage;
            AdminTools.getInstance().AdminchatLogIn = adminchatlogin;
            AdminTools.getInstance().AdminchatLogOff = adminchatlogoff;
            AdminTools.getInstance().AdminchatAlreadyLogOff = adminchatalreadylogoff;
            AdminTools.getInstance().AdminchatAlreadyLogIn = adminchatalreadylogon;
            AdminTools.getInstance().ToggleMsgMessage = toggleMsgMessage;
            AdminTools.getInstance().OtherToggleMsgMessage = otherToggleMsgMessage;
            AdminTools.getInstance().NoMsgMessage = noMsgMessage;
            AdminTools.getInstance().Banned = banned;
            AdminTools.getInstance().MySQLcon = mysql;
            AdminTools.getInstance().ActivateMaintenance = maintenance;
            AdminTools.getInstance().StatusMaintenance = statusMaintenance;
            AdminTools.getInstance().NoMaintenanceModeSet = nomaintenancemode;
            AdminTools.getInstance().MaintenanceAdd = maintenanceadd;
            AdminTools.getInstance().MaintenanceRemove = maintenanceremove;
            AdminTools.getInstance().MaintenanceAlreadyOff = maintenancealreadyoff;
            AdminTools.getInstance().MaintenanceAlreadyOn = maintenancealreadyon;
            AdminTools.getInstance().MaintenanceAlreadySet = maintenancealreadyset;
            AdminTools.getInstance().MaintenancePlayerNotSet = maintenanceplayernotset;
            AdminTools.getInstance().MaintenanceOn = maintenanceon;
            AdminTools.getInstance().MaintenanceOff = maintenanceoff;
            AdminTools.getInstance().MaintenanceMotdLine1 = maintenanceMotdLine1;
            AdminTools.getInstance().MaintenanceMotdLine2 = maintenanceMotdLine2;
            AdminTools.getInstance().MaintenanceKickHeader = maintenanceKickHeader;
            AdminTools.getInstance().MaintenanceKickLine1 = maintenanceKickLine1;
            AdminTools.getInstance().MaintenanceKickLine2 = maintenanceKickLine2;
            AdminTools.getInstance().MaintenanceKickLine3 = maintenanceKickLine3;
            AdminTools.getInstance().MaintenanceKickFooter = maintenanceKickFooter;
            AdminTools.getInstance().AdmintoolsItemName = admintoolsitemname;
            AdminTools.getInstance().AdmintoolsItemAdded = admintoolsitemadd;
            AdminTools.getInstance().AdmintoolsItemAlreadyInInventory = admintoolsitemalreadyin;
            AdminTools.getInstance().GetAdmintoolsItemOnSpawn = getitemonjoin;
            AdminTools.getInstance().CurrentWorldClearMessage = currentworldclear;
            AdminTools.getInstance().AllWorldClearMessage = allworldclear;
        }
        catch (NullPointerException ex)
        {
            new FileCopy().copy(AdminTools.getInstance().getResource("config.yml"), getConfigFile());
            Bukkit.getConsoleSender().sendMessage(" ");
            Bukkit.getConsoleSender().sendMessage("§7[§2AdminTools§7]§c§l ATTENTION");
            Bukkit.getConsoleSender().sendMessage(" ");
            Bukkit.getConsoleSender().sendMessage("§7[§2AdminTools§7]§c§l CONFIG ERROR. RESETTET CONFIG TO DEFAULT. THE SERVER WILL STOP IN 10 SECONDS. PLEASE RESTART IT");
            Bukkit.getConsoleSender().sendMessage(" ");
            Bukkit.getConsoleSender().sendMessage("§7[§2AdminTools§7]§c§l ATTENTION");
            Bukkit.getConsoleSender().sendMessage(" ");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Bukkit.getServer().shutdown();
                }
            }, 10000);
            return;
        }
    }
    public static void setMaintenanceConfig()
    {
        FileConfiguration cfg = getMaintenanceFileConfig();
        cfg.options().copyDefaults(true);
        cfg.addDefault("maintenance", false);
        try
        {
            cfg.save(getMaintenanceFile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void readMaintenance()
    {
        FileConfiguration cfg = getMaintenanceFileConfig();
        boolean maintenance = cfg.getBoolean("maintenance");
        AdminTools.getInstance().MaintenanceMode = maintenance;
    }
    public static void setMySQL()
    {
        FileConfiguration cfg = getMySQLConfig();
        cfg.options().copyDefaults(true);
        cfg.addDefault("username", "root");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "localhost");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");

        try
        {
            cfg.save(getMySQL());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void readMySQL()
    {
        FileConfiguration cfg   = getMySQLConfig();
        MySQL.username          = cfg.getString("username");
        MySQL.password          = cfg.getString("password");
        MySQL.database          = cfg.getString("database");
        MySQL.host              = cfg.getString("host");
        MySQL.port              = cfg.getString("port");
    }

}
