package ru.owopeef.owochestcommands.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.owopeef.owochestcommands.Inventories;

public class Commands implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player)) { return true; }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("menu"))
        {
            if (args.length == 0)
            {
                sender.sendMessage("Specify the name of the menu!");
                return true;
            }
            if (args.length == 1) {
                int menuNumber;
                try
                {
                    menuNumber = Integer.parseInt(args[0]);
                }
                catch (Exception e)
                {
                    System.out.println(e.getMessage());
                    return true;
                }
                try
                {
                    player.openInventory(Inventories.inventories.get(menuNumber));
                    return true;
                }
                catch (IndexOutOfBoundsException e)
                {
                    sender.sendMessage("Меню под цифрой " + menuNumber + " не найдено.");
                }
            }
        }
        return true;
    }
}
