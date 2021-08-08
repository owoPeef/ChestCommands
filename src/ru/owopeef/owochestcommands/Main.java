package ru.owopeef.owochestcommands;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.owochestcommands.commands.Commands;

import java.io.File;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        loadConfig();
        Inventories.createInventories();
        getCommand("menu").setExecutor(new Commands());
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
    }
    public void loadConfig() {
        File currentFile = new File(System.getProperty("user.dir") + "\\plugins\\owoChestCommands\\config.yml");
        if (!currentFile.exists())
        {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }
}
