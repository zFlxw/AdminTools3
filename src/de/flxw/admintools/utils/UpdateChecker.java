package de.flxw.admintools.utils;

import com.google.common.net.UrlEscapers;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class UpdateChecker {
    private AdminTools plugin;
    private int resourceId;
    public UpdateChecker(AdminTools plugin, int resourceId)
    {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer)
    {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream))
            {
                if(scanner.hasNext())
                {
                    consumer.accept(scanner.next());
                }
            }
            catch (IOException ex)
            {
                this.plugin.getLogger().info("Error while checking for Updates: " + ex.getMessage());
            }
        });
    }

}
