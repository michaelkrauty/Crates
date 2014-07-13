package me.michaelkrauty.Crates;

import me.michaelkrauty.Crates.commands.GiveCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created on 7/12/2014.
 *
 * @author michaelkrauty
 */
public class CratesCommand implements CommandExecutor {

	private static Main main;

	public CratesCommand(Main main) {
		this.main = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args[0].equalsIgnoreCase("give")) {
			new GiveCommand(main, sender, args);
			return true;
		}
		sender.sendMessage(ChatColor.RED + "/crate give <player>");
		return true;
	}
}
