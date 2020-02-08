package de.flxw.admintools.commands;

import de.flxw.admintools.utils.ATCenterInv;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Command_Bypass implements CommandExecutor {
    public Command_Bypass(AdminTools adminTools) { }
    private static final String PERM_BYPASS_ALL = "admintools.bypass.*";
    private static final String PERM_BYPASS_ALL2 = "admintools.bypass.all";
    private static final String PERM_BYPASS_GM = "admintools.bypass.gm";
    private static final String PERM_BYPASS_FLY = "admintools.bypass.fly";
    private static final String PERM_BYPASS_GOD = "admintools.bypass.god";
    private static final String PERM_BYPASS_LOCKCHAT = "admintools.bypass.lockchat";
    private static final String PERM_BYPASS_VANISH = "admintools.bypass.vanish";
    private static final String PERM_BYPASS_INVSEE = "admintools.bypass.invsee";
    private static final String PERM_BYPASS_KILL = "admintools.bypass.kill";
    private static final String PERM_BYPASS_CENSOR = "admintools.bypass.censor";
    private static final String PERM_BYPASS_FREEZE = "admintools.bypass.freeze";
    private static final String PERM_BYPASS_SPEED = "admintools.bypass.speed";
    private static final String PERM_BYPASS_HEAL = "admintools.bypass.heal";
    private static final String PERM_BYPASS_FEED = "admintools.bypass.feed";
    private static final String PERM_BYPASS_LIST = "admintools.bypass.list";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender instanceof Player)
        {
            Player player = (Player)commandSender;
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("gm") || args[0].equalsIgnoreCase("gamemode"))
                {
                    if(player.hasPermission(PERM_BYPASS_GM) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassGamemode.contains(player))
                        {
                            ArrayLists.bypassGamemode.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "gamemode"));
                        }
                        else
                        {
                            ArrayLists.bypassGodmode.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "gamemode"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("fly"))
                {
                    if(player.hasPermission(PERM_BYPASS_FLY) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassFly.contains(player))
                        {
                            ArrayLists.bypassFly.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "fly"));
                        }
                        else
                        {
                            ArrayLists.bypassFly.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "fly"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("god")|| args[0].equalsIgnoreCase("godmode"))
                {
                    if(player.hasPermission(PERM_BYPASS_GOD) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassGodmode.contains(player))
                        {
                            ArrayLists.bypassGodmode.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "godmode"));
                        }
                        else
                        {
                            ArrayLists.bypassGodmode.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "godmode"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("lockchat") || args[0].equalsIgnoreCase("lc"))
                {
                    if(player.hasPermission(PERM_BYPASS_LOCKCHAT) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassLockchat.contains(player))
                        {
                            ArrayLists.bypassLockchat.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "lockchat"));
                        }
                        else
                        {
                            ArrayLists.bypassLockchat.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "lockchat"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("vanish"))
                {
                    if(player.hasPermission(PERM_BYPASS_VANISH) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassVanish.contains(player))
                        {
                            ArrayLists.bypassVanish.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "vanish"));
                        }
                        else
                        {
                            ArrayLists.bypassVanish.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "vanish"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }

                }
                else if(args[0].equalsIgnoreCase("heal"))
                {
                    if(player.hasPermission(PERM_BYPASS_HEAL) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassHeal.contains(player))
                        {
                            ArrayLists.bypassHeal.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "heal"));
                        }
                        else
                        {
                            ArrayLists.bypassHeal.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "heal"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }

                }
                else if(args[0].equalsIgnoreCase("feed"))
                {
                    if(player.hasPermission(PERM_BYPASS_FEED) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassFeed.contains(player))
                        {
                            ArrayLists.bypassFeed.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "feed"));
                        }
                        else
                        {
                            ArrayLists.bypassFeed.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "feed"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }

                }
                else if(args[0].equalsIgnoreCase("invsee"))
                {
                    if(player.hasPermission(PERM_BYPASS_INVSEE) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassInvsee.contains(player))
                        {
                            ArrayLists.bypassInvsee.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "invsee"));
                        }
                        else
                        {
                            ArrayLists.bypassInvsee.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "invsee"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("kill"))
                {
                    if(player.hasPermission(PERM_BYPASS_KILL) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassKill.contains(player))
                        {
                            ArrayLists.bypassKill.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "kill"));
                        }
                        else
                        {
                            ArrayLists.bypassKill.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "kill"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("censor"))
                {
                    if(player.hasPermission(PERM_BYPASS_CENSOR) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassCensor.contains(player))
                        {
                            ArrayLists.bypassCensor.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "censor"));
                        }
                        else
                        {
                            ArrayLists.bypassCensor.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "censor"));
                        }
                    }

                }
                else if(args[0].equalsIgnoreCase("freeze"))
                {
                    if(player.hasPermission(PERM_BYPASS_FREEZE) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassFreeze.contains(player))
                        {
                            ArrayLists.bypassFreeze.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "freeze"));
                        }
                        else
                        {
                            ArrayLists.bypassFreeze.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "freeze"));
                        }
                    }
                }
                else if(args[0].equalsIgnoreCase("speed"))
                {
                    if(player.hasPermission(PERM_BYPASS_SPEED) || player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassSpeed.contains(player))
                        {
                            ArrayLists.bypassSpeed.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "speed"));
                        }
                        else
                        {
                            ArrayLists.bypassSpeed.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "speed"));
                        }
                    }
                }
                else if(args[0].equalsIgnoreCase("list"))
                {
                    if(player.hasPermission(PERM_BYPASS_LIST) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        ATCenterInv.AdminBypassInv(player);
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("all"))
                {
                    if(player.hasPermission(PERM_BYPASS_ALL) || player.hasPermission(PERM_BYPASS_ALL2) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        if(!ArrayLists.bypassAll.contains(player))
                        {
                            ArrayLists.bypassAll.add(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().BypassMessage.replaceAll("%bypass%", "all commands"));
                        }
                        else
                        {
                            ArrayLists.bypassAll.remove(player);
                            player.sendMessage(AdminTools.getInstance().Prefix + " " + AdminTools.getInstance().NoBypassMessage.replaceAll("%bypass%", "all commands"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§c/bypass <(bypass)/list/all>");
                }
            }
            else
            {
                ATCenterInv.AdminBypassInv(player);
            }
        }
        else
        {
            if(args[0].equalsIgnoreCase("list"))
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7§l-----[ §cList of Bypass Arguments §7§l]-----");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cgm");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cfly");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cgod");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §clockchat");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cvanish");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cheal");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cfeed");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cinvsee");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §ckill");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §ccensor");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §cfreeze");
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§7- §call");
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().NoPlayer);
            }
        }
        return true;
    }
}
