package de.flxw.admintools.ban;

import de.flxw.admintools.mysql.MySQL;
import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BanManager {
    public static void ban(String uuid, String playername, String Staff, String reason, long seconds)
    {
        long end = 0;
        if (seconds == -1)
        {
            end = -1;
        }
        else
        {
            long current = System.currentTimeMillis();
            long millis = seconds*1000;
            end = current+millis;
        }
        MySQL.update("INSERT INTO BannedPlayers (PLAYERNAME, UUID, ENDBAN, REASON, STAFF) VALUES ('"+playername+"','"+uuid+"','"+end+"','"+reason+"','"+Staff+"')");
        if(Bukkit.getPlayer(playername) != null)
        {
            String banHeader = AdminTools.getInstance().BanHeader;
            String banReason = AdminTools.getInstance().BanReason.replaceAll("%reason%", getReason(uuid));
            String bannedBy = AdminTools.getInstance().BannedBy.replaceAll("%bannedby%", Staff);
            String remaining = AdminTools.getInstance().RemainingBan.replaceAll("%enddate%", getUnbandate(uuid));
            String appeal = AdminTools.getInstance().BanAppeal;
            Bukkit.getPlayer(playername).kickPlayer(banHeader + "\n\n\n" + banReason + "\n\n" + bannedBy + "\n\n\n" + remaining + "\n\n" + appeal);
        }
    }

    public static void unban(String uuid)
    {
        MySQL.update("DELETE FROM BannedPlayers WHERE UUID='"+uuid+"'");
    }

    public static boolean isBanned(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");

        try {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getReason(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");

        try {
            while (rs.next())
            {
                return rs.getString("REASON");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static Long getEnd(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='" + uuid + "'");
        try
        {
            while(rs.next())
            {
                return rs.getLong("ENDBAN");
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static List<String> getBannedPlayers()
    {
        List<String> list = new ArrayList<>();
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers");

        try
        {
            while(rs.next())
            {
                list.add(rs.getString("PLAYERNAME"));
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return list;
    }

    public static String getStaff(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM BannedPlayers WHERE UUID='"+uuid+"'");

        try {
            while(rs.next())
            {
                return rs.getString("STAFF");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "";
    }

    public static String getUnbandate(String uuid) {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        if(end == -1)
        {
            return "§4§lPERMANENT";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy HH:mm");
        Calendar now = Calendar.getInstance();
        sdf.setTimeZone(now.getTimeZone());
        return sdf.format(end);
    }

    public static String getRemainingTime(String uuid)
    {
        long current = System.currentTimeMillis();
        long end = getEnd(uuid);
        if(end == -1)
        {
            return "§4§lPERMANENT";
        }
        long millis = end - current;

        long seconds = 0;
        long minutes = 0;
        long hours = 0;
        long days = 0;
        long weeks = 0;
        while (millis > 1000)
        {
            millis-= 1000;
            seconds++;
        }
        while (seconds > 60)
        {
            seconds-=60;
            minutes++;
        }
        while (minutes > 60)
        {
            minutes-=60;
            hours++;
        }
        while (hours > 24)
        {
            hours-=24;
            days++;
        }
        while (days > 7)
        {
            days-=7;
            weeks++;
        }
        return "§4" + weeks + " §cWeek(s) §4" + days + " §cDay(s) §4" + hours + " §cHour(s) §4" + minutes + " §cMinute(s) §4" + seconds + " §cSecond(s)";
    }

}
