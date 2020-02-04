package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Heal implements CommandExecutor {
    public Command_Heal(AdminTools adminTools) {}

    private static final String PERM_HEAL = "admintools.heal";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;

            if(player.hasPermission(PERM_HEAL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 0)
                {
                    player.setHealth(20);
                    player.setFoodLevel(20);
                    player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().HealMessage);
                }
                else if (args.length == 1)
                {
                    Player target = Bukkit.getPlayer(args[0]);

                    if(args[0].equals("*"))
                    {
                        for (Player all: Bukkit.getOnlinePlayers()) {
                            if(!ArrayLists.bypassHeal.contains(all) && !ArrayLists.bypassAll.contains(all))
                            {
                                all.setHealth(20);
                                all.setFoodLevel(20);
                                all.sendMessage(AdminTools.getInstance().HealMessage);
                            }
                        }
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AllHealMessage);
                    }
                    else
                    {
                        if(target != null)
                        {
                            if(!ArrayLists.bypassHeal.contains(target) && !ArrayLists.bypassAll.contains(target))
                            {
                                target.setHealth(20);
                                target.setFoodLevel(20);
                                target.sendMessage(AdminTools.getInstance().HealMessage);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherHealMessage.replaceAll("%player%", target.getName()));
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "heal"));
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().PlayerNotOnline);
                        }
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /heal (Player)");
                }
            }
            else
            {
                player.sendMessage(AdminTools.getInstance().NoPerm);
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPlayer);
        }

        return true;
    }
}
