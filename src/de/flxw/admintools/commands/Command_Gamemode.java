package de.flxw.admintools.commands;

import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.ArrayLists;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Gamemode implements CommandExecutor {
    public Command_Gamemode(AdminTools adminTools) {
    }

    private static final String PERM_GM = "admintools.gm";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player)
        {
            Player player = (Player) commandSender;
            if(player.hasPermission(PERM_GM) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
            {
                if(args.length == 1)
                {
                    if(args[0].equalsIgnoreCase("1"))
                    {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()));
                    }
                    else if (args[0].equalsIgnoreCase("2"))
                    {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()));
                    }
                    else if (args[0].equalsIgnoreCase("3"))
                    {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()));
                    }
                    else if (args[0].equalsIgnoreCase("0"))
                    {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()));
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /gm <1/2/3/0> (Player)");
                    }
                }
                else if (args.length == 2)
                {
                    Player target = Bukkit.getPlayer(args[1]);

                    if(target != null)
                    {
                        if(!ArrayLists.bypassGamemode.contains(target) && !ArrayLists.bypassAll.contains(target))
                        {
                            if(args[0].equalsIgnoreCase("1"))
                            {
                                target.setGameMode(GameMode.CREATIVE);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherGamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()).replace("%player%", target.getName()));
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replace("%gamemode%", target.getGameMode().toString()));
                            }
                            else if (args[0].equalsIgnoreCase("2"))
                            {
                                target.setGameMode(GameMode.ADVENTURE);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherGamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()).replace("%player%", target.getName()));
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replace("%gamemode%", target.getGameMode().toString()));
                            }
                            else if (args[0].equalsIgnoreCase("3"))
                            {
                                target.setGameMode(GameMode.SPECTATOR);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherGamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()).replace("%player%", target.getName()));
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replace("%gamemode%", target.getGameMode().toString()));
                            }
                            else if (args[0].equalsIgnoreCase("0"))
                            {
                                target.setGameMode(GameMode.SURVIVAL);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().OtherGamemodeMessage.replaceAll("%gamemode%", player.getGameMode().toString()).replace("%player%", target.getName()));
                                target.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().GamemodeMessage.replace("%gamemode%", target.getGameMode().toString()));
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /gm <1/2/3/0> (Player)");
                            }
                        }
                        else
                        {
                            player.sendMessage(AdminTools.getInstance().IsBypassingMessage.replace("%bypass%", "gamemode"));
                        }
                    }
                    else
                    {
                        player.sendMessage(AdminTools.getInstance().PlayerNotOnline);
                    }
                }
                else
                {
                    player.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /gm <1/2/3/0> (Player)");
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
