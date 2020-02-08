package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Command_Freeze implements CommandExecutor {
    public Command_Freeze(AdminTools adminTools) {}
    private static final String PERM_FREEZE = "admintools.freeze";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_FREEZE) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL)) {
            if(args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if(target != null)
                {
                    if(!ArrayLists.bypassFreeze.contains(target) && !ArrayLists.bypassAll.contains(target))
                    {
                        if(ArrayLists.freeze.contains(target)) {

                            ArrayLists.freeze.remove(target);
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherNoFreezeMessage.replaceAll("%player%", target.getName()));
                            target.sendMessage(AdminTools.getInstance().NoFreezeMessage);
                            target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 0, 0), true);
                        }
                        else
                        {
                            ArrayLists.freeze.add(target);
                            commandSender.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherFreezeMessage.replaceAll("%player%", target.getName()));
                            target.sendMessage(AdminTools.getInstance().FreezeMessage);
                            target.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 10000000, 129), true);
                        }
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().IsBypassingMessage.replaceAll("%bypass%", "freeze"));
                    }
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                }
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /freeze <player>");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
