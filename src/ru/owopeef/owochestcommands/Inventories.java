package ru.owopeef.owochestcommands;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventories
{
    static Plugin plugin = Main.getPlugin(Main.class);
    public static List<Inventory> inventories = new ArrayList<>();
    public static void createInventories()
    {
        int a = 0;
        int b = 0;
        String menuSlots;
        String menuTitle;
        String menuItemMaterial;
        String menuItemName;
        String menuItemSlot;
        while (true)
        {
            menuSlots = readConfig("menus", String.valueOf(a), "slots");
            menuTitle = readConfig("menus", String.valueOf(a), "title");
            if (Objects.equals(menuSlots, "break") || Objects.equals(menuTitle, "break"))
            {
                break;
            }
            Inventory inv = plugin.getServer().createInventory(null, Integer.parseInt(menuSlots) * 9, menuTitle);
            b = 0;
            while (true)
            {
                menuItemMaterial = readConfig("menus." + a + ".items", String.valueOf(b), "material").toUpperCase();
                menuItemName = readConfig("menus." + a + ".items", String.valueOf(b), "name").replace("&", "§");
                menuItemSlot = readConfig("menus." + a + ".items", String.valueOf(b), "slot");
                if (Objects.equals(menuItemMaterial, "break") || Objects.equals(menuItemName, "break") || Objects.equals(menuItemSlot, "break"))
                {
                    break;
                }
                try
                {
                    ItemStack item = new ItemStack(Material.getMaterial(menuItemMaterial));
                    ItemMeta IM = item.getItemMeta();
                    IM.setDisplayName(menuItemName);
                    item.setItemMeta(IM);
                    inv.setItem(Integer.parseInt(menuItemSlot), item);
                }
                catch (NullPointerException e)
                {
                    int itemNumber = b + 1;
                    plugin.getLogger().warning("Material " + menuItemMaterial + " not found, item number " + itemNumber + " skipped.");
                }
                b++;
            }
            inventories.add(inv);
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
