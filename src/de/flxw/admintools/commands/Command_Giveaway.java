package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class Command_Giveaway implements CommandExecutor {
    String price = "";
    int high = AdminTools.getInstance().GiveawayCounter;
    int GIVEAWAY;
    public Command_Giveaway(AdminTools adminTools) {}
    private static final String PERM_GIVEAWAY = "admintools.giveaway";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_GIVEAWAY) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length >= 1)
            {
                Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
                Random rndm = new Random();

                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < args.length; i++)
                {
                    sb.append(args[i]).append(" ");
                }
                price = sb.toString().trim();
                if(!onlinePlayers.isEmpty() && !(onlinePlayers.size() == 1))
                {
                    int pos = rndm.nextInt(onlinePlayers.size());
                    Iterator<? extends Player> iterator = onlinePlayers.iterator();
                    for(int i = 0; i < pos; i++)
                    {
                        iterator.next();
                    }
                    Player winner = iterator.next();

                    commandSender.sendMessage(AdminTools.getInstance().GiveawayStarted.replaceAll("%price%", price));
                    GIVEAWAY = Bukkit.getScheduler().scheduleSyncRepeatingTask(AdminTools.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            if(high != 0)
                            {
                                high--;
                                for(Player all : Bukkit.getOnlinePlayers())
                                {
                                    String giveawayCount = AdminTools.getInstance().GiveawayCount.replaceAll("%seconds%", Integer.toString(high));
                                    all.sendMessage(giveawayCount);
                                }
                            }
                            else
                            {
                                String giveawayMessage = AdminTools.getInstance().GiveawayWinMessage.replace("%player%", winner.getName()).replace("%price%", price);
                                for(Player all : Bukkit.getOnlinePlayers())
                                {
                                    all.sendMessage(giveawayMessage);
                                }
                                Bukkit.getScheduler().cancelTask(GIVEAWAY);
                                high = AdminTools.getInstance().GiveawayCounter;
                                price = "";
                            }
                        }
                    }, 20, 20);
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().GiveawayNotEnough);
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /giveaway <Price>");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
