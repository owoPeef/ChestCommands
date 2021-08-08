package ru.owopeef.owochestcommands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.Objects;

public class EventsClass implements Listener
{
    static Plugin plugin = Main.getPlugin(Main.class);
    String currentInventoryName;
    Inventory currentInventory;
    @EventHandler
    public void InventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory open = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        int a = 0;
        int b = 0;
        String itemName;
        String itemCommand;
        while (a != Inventories.inventories.size())
        {
            currentInventory = Inventories.inventories.get(a);
            currentInventoryName = currentInventory.getName();
            if (open == null) { return; }
            if (open.getName().equals(currentInventoryName))
            {
                event.setCancelled(true);
                if (item == null || !item.hasItemMeta())
                {
                    return;
                }
                while (true)
                {
                    itemName = readConfig("menus." + a + ".items", String.valueOf(b), "name");
                    itemCommand = readConfig("menus." + a + ".items", String.valueOf(b), "command").replace("/", "");
                    if (Objects.equals(itemName, "break") || Objects.equals(itemCommand, "break"))
                    {
                        break;
                    }
                    if (item.getItemMeta().getDisplayName().equals(itemName))
                    {
                        Bukkit.dispatchCommand(player, itemCommand);
                    }
                    b++;
                }
            }
            a++;
        }
    }
    public static String readConfig(String path, String parent, String parent2)
    {
        try
        {
            return plugin.getConfig().get(path + "." + parent + "." + parent2).toString();
        }
        catch (Exception e)
        {
            return "break";
        }
    }
}
