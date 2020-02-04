package de.flxw.admintools.utils;

import de.flxw.admintools.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlayerInfoManager {

    public static boolean isPlayerInTable(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                return true;
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
    public static String getPlayerName(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                return rs.getString("PLAYERNAME");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "Error: Not in table";
    }
    public static String getPlayerUuid(String playername)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE PLAYERNAME='"+playername+"'");
        try
        {
            while(rs.next())
            {
                return rs.getString("UUID");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "Error: Not in table";
    }
    public static String getPlayerIp(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        if(AdminTools.getInstance().LogIP)
        {
            try
            {
                while(rs.next())
                {
                    return rs.getString("IP_ADDRESS");
                }
            }
            catch(SQLException ex)
            {
                ex.printStackTrace();
            }
        }
        else
        {
            return "LogIP is disabled";
        }
        return "Error: Not in table";
    }
    public static String getFirstJoined(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                return rs.getString("FIRST_JOINED");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "Error: Not in table";
    }

    public static String getLastSeen(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                return rs.getString("LAST_JOINED");
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "Error: Not in table";
    }

    public static String getPlayerFirstJoinDate(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                long date = rs.getLong("FIRST_JOINED");
                SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy HH:mm:ss");
                Calendar now = Calendar.getInstance();
                sdf.setTimeZone(now.getTimeZone());
                Date result = new Date(date);
                return sdf.format(date) + " (" + now.getTimeZone().getDisplayName() + ")";
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "Error: Not in table";
    }

    public static String getPlayerLastJoinDate(String uuid)
    {
        ResultSet rs = MySQL.getResult("SELECT * FROM PlayerInfo WHERE UUID='"+uuid+"'");
        try
        {
            while(rs.next())
            {
                long date = rs.getLong("LAST_JOINED");
                SimpleDateFormat sdf = new SimpleDateFormat("dd. MMMM yyyy HH:mm:ss");
                Calendar now = Calendar.getInstance();
                sdf.setTimeZone(now.getTimeZone());
                Date result = new Date(date);
                return sdf.format(date) + " (" + now.getTimeZone().getDisplayName() + ")";
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return "Error: Not in table";
    }
}
