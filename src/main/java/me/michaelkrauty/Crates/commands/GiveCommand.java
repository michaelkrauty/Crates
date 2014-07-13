package me.michaelkrauty.Crates.commands;

import me.michaelkrauty.Crates.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * Created on 7/12/2014.
 *
 * @author michaelkrauty
 */
public class GiveCommand {
	public GiveCommand(Main main, CommandSender sender, String[] args) {
		if (sender.hasPermission("crates.give")) {
			if (args.length == 2) {
				Player player;
				// Is the player online?
				if ((player = main.getServer().getPlayer(args[1])) instanceof Player) {
					Inventory inventory = player.getInventory();
					// Is there space in their inventory?
					if (inventory.firstEmpty() != -1) {
						// Give the crate
						main.giveCrate(inventory);
						sender.sendMessage(ChatColor.GRAY + "Gave " + player.getName() + " a crate.");
						return;
					}
					sender.sendMessage(ChatColor.RED + "That player doesn't have space in their inventory for a crate!");
				}
				sender.sendMessage(ChatColor.RED + "That player isn't online!");
				return;
			}
			sender.sendMessage(ChatColor.RED + "Usage: /crate give <player>");
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have access to that command!");
		return;
	}
}
