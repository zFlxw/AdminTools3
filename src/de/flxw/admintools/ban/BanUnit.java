package de.flxw.admintools.ban;

import java.util.ArrayList;
import java.util.List;

public enum BanUnit {

    SECONDS("Second(s)", 1, "s"),
    MINUTES("Minute(s)", 60, "m"),
    HOURS("Hour(s)", 60*60, "h"),
    DAYS("Day(s)", 24*60*60, "d"),
    WEEKS("Week(s)", 7*24*60*60, "w");

    private String name;
    private int toSecond;
    private String shortcut;

    private BanUnit(String name, int toSecond, String shortcut)
    {
        this.name = name;
        this.toSecond = toSecond;
        this.shortcut = shortcut;
    }

    public int getToSecond()
    {
        return toSecond;
    }
    private String getName()
    {
        return name;
    }
    private String getShortcut()
    {
        return shortcut;
    }
    public static List<String> getUnitsAsString()
    {
        List<String> units = new ArrayList<>();
        for (BanUnit unit : BanUnit.values())
        {
            units.add(unit.getShortcut());
        }
        return units;
    }
    public static BanUnit getUnit(String unit)
    {
        for (BanUnit units : BanUnit.values())
        {
            if(units.getShortcut().toLowerCase().equals(unit.toLowerCase()))
            {
                return units;
            }
        }
        return null;
    }
}
