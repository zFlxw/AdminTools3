package de.flxw.admintools.commands;

import de.flxw.admintools.utils.ATCenterInv;
import de.flxw.admintools.utils.AdminTools;
import de.flxw.admintools.utils.FileManager;
import de.flxw.admintools.utils.UpdateChecker;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Command_Admintools implements CommandExecutor {
    public Command_Admintools(AdminTools adminTools) {}
    public String PLUGIN_VERSION = AdminTools.getInstance().getDescription().getVersion();
    public final String DISCORD = "Flxw#7928";
    private static final String PERM_TOOLS = "admintools.tools";
    private static final String PERM_TOOLS_HELP = "admintools.tools.help";
    private static final String PERM_TOOLS_ALL = "admintools.tools.*";
    private static final String PERM_TOOLS_VERSION = "admintools.tools.version";
    private static final String PERM_TOOLS_ATGUI = "admintools.tools.atgui";

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args)
    {
        if(commandSender.hasPermission(PERM_TOOLS) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
        {
            if(args.length == 1)
            {
                if(args[0].equalsIgnoreCase("help"))
                {
                    if(commandSender instanceof Player)
                    {
                        if(commandSender.hasPermission(PERM_TOOLS_HELP) || commandSender.hasPermission(PERM_TOOLS_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                        {
                            Player player = (Player) commandSender;
                            TextComponent tc = new TextComponent();
                            tc.setText(AdminTools.getInstance().Prefix + "§8» §aClick here for help");
                            tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://docs.google.com/spreadsheets/d/1kyL7coOOPAmwUahXgGZgq6Uk8q2X9fcozgcH0gd-qyI/edit?usp=sharing"));
                            player.spigot().sendMessage(tc);
                            player.sendMessage(AdminTools.getInstance().Prefix + "§8» §aYou have another question? Contact me on Discord!");
                            player.sendMessage(AdminTools.getInstance().Prefix + "§8» §5Discord: §d" + DISCORD);
                        }
                        else
                        {
                            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                        }
                    }
                    else
                    {
                        commandSender.sendMessage("§aCopy this Link and paste it into it into your browser: https://docs.google.com/spreadsheets/d/1kyL7coOOPAmwUahXgGZgq6Uk8q2X9fcozgcH0gd-qyI/edit?usp=sharing");
                    }
                }
                else if(args[0].equalsIgnoreCase("version"))
                {
                    if(commandSender.hasPermission(PERM_TOOLS_VERSION) || commandSender.hasPermission(PERM_TOOLS_ALL) || commandSender.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        commandSender.sendMessage(AdminTools.getInstance().Prefix + "§8» §aInstalled plugin Version: §e" + PLUGIN_VERSION);
                        new UpdateChecker(AdminTools.getInstance(), AdminTools.getInstance().resourceId).getVersion(version -> {
                            if(!AdminTools.getInstance().getDescription().getVersion().equalsIgnoreCase(version))
                            {
                                if(commandSender instanceof Player)
                                {
                                    Player player = (Player) commandSender;
                                    TextComponent tc = new TextComponent();
                                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/admintools-mysql.68455/"));
                                    tc.setText(AdminTools.getInstance().Prefix + "§8» §cnew version is available. Click here to download");
                                    player.spigot().sendMessage(tc);
                                }
                                else
                                {
                                    commandSender.sendMessage("§8» §ca newer version is available. Download here: https://www.spigotmc.org/resources/admintools-mysql.68455/");
                                }
                            }
                            else
                            {
                                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§8» §aYou're on the newest version!");
                            }
                        });
                    }
                    else
                    {
                        commandSender.sendMessage(AdminTools.getInstance().NoPerm);
                    }
                }
                else if(args[0].equalsIgnoreCase("item"))
                {
                    if(commandSender instanceof Player)
                    {
                        Player player = (Player) commandSender;
                        if(player.hasPermission(PERM_TOOLS_ATGUI) || player.hasPermission(PERM_TOOLS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                        {
                            ItemStack atItem = new ItemStack(Material.COMPASS);
                            ItemMeta atItemMeta = atItem.getItemMeta();
                            atItemMeta.setDisplayName(AdminTools.getInstance().AdmintoolsItemName);
                            atItemMeta.setLore(Arrays.asList("§0v"+ AdminTools.getInstance().getDescription().getVersion()));
                            atItem.setItemMeta(atItemMeta);
                            if(!player.getInventory().contains(atItem))
                            {
                                player.getInventory().addItem(atItem);
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdmintoolsItemAdded);
                            }
                            else
                            {
                                player.sendMessage(AdminTools.getInstance().Prefix + AdminTools.getInstance().AdmintoolsItemAlreadyInInventory);
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
                }
                else
                {
                    commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /admintools <help/version/Discord>");
                }
            }
            else if(args.length == 0)
            {
                if(commandSender instanceof Player)
                {
                    Player player = (Player) commandSender;

                    if(player.hasPermission(PERM_TOOLS_ATGUI) || player.hasPermission(PERM_TOOLS_ALL) || player.hasPermission(AdminTools.getInstance().PERM_ALL))
                    {
                        ATCenterInv.AdminMainInv(player);
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
            }
            else
            {
                commandSender.sendMessage(AdminTools.getInstance().Prefix + "§cUsage: /admintools <help/version/Discord>");
            }
        }
        else
        {
            commandSender.sendMessage(AdminTools.getInstance().NoPerm);
        }
        return true;
    }
}
