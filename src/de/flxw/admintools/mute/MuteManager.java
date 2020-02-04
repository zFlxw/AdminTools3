package de.flxw.admintools.mute;

import de.flxw.admintools.mysql.MySQL;
import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MuteManager {
    public static void mute(String uuid, String playername, String Staff, String Reason, long seconds)
    {
        long end;
        if(seconds == -1)
        {
            end = -1;
        }
        else
        {
            long current = System.currentTimeMillis();
            long millis = seconds*1000;
            end = current+millis;
        }
        MySQL.update("INSERT INTO MutedPlayers (PLAYERNAME, UUID, ENDMUTE, REASON, STAFF) VALUES ('"+playername+"','"+uuid+"','"+end+"','"+Reason+"','"+Staff+"')");
        if(Bukkit.getPlayer(playername) != null)
        {
            String MuteMessage = AdminTools.getInstance().MuteMessage.replaceAll("%reason%", getReason(uuid)).replaceAll("%enddate%", getUnbandate(uuid));
            MuteMessage = MuteMessage.replaceAll("%enddate%", getUnbandate(uuid));
            Bukkit.getPlayer(playername).sendMessage(" ");
            Bukkit.getPlayer(playername).sendMessage(MuteMessage);
            Bukkit.getPlayer(playername).sendMessage(" ");
        }
    }
    public static void unmute(String uuid)
    {
        MySQL.update("DELETE FROM MutedPlayers WHERE UUID='"+uuid+"'");
    }
    public static boolean isMuted(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='"+uuid+"'");

        try
        {
            return rs.next();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static String getReason(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
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
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                return rs.getLong("ENDMUTE");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
    public static List<String> getMutedPlayers()
    {
        List<String> list = new ArrayList<>();
        ResultSet rs = MySQL.getResult("SELECT * FROM MutedPlayers");

        try
        {
            while(rs.next())
            {
                list.add(rs.getString("PLAYERNAME"));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return list;
    }
    public static String getUnbandate(String uuid)
    {
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
}
