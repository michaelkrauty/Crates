package me.michaelkrauty.Crates;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

/**
 * Created on 7/12/2014.
 *
 * @author michaelkrauty
 */
public class PlayerListener implements Listener {

	private final Main main;

	public PlayerListener(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (event.getItem() != null) {
			ItemStack item = event.getItem();
			if (item.getType() != null) {
				if (item.getType() == Material.CHEST) {
					ItemMeta meta = item.getItemMeta();
					if (meta.getDisplayName().equals("Magic Crate")) {
						if (meta.getLore().equals(Arrays.asList("A Magical Storage Box"))) {

							// Ladies and gentlemen, we have a crate.

							event.setCancelled(true);
							Set set = main.getConfig().getConfigurationSection("items").getKeys(true);
							ArrayList<String> blocks = new ArrayList<String>();
							ArrayList<Integer> amounts = new ArrayList<Integer>();
							for (Object str : set) {
								System.out.println((String) str);
								blocks.add((String) str);
								amounts.add(main.getConfig().getInt("items." + str));
							}

							int index = new Random().nextInt(set.size());

							Inventory crate = main.getServer().createInventory(null, 9, "Magic Crate");
							crate.setItem(4, new ItemStack(Material.getMaterial(blocks.get(index)), amounts.get(index)));
							event.getPlayer().openInventory(crate);
							event.getPlayer().getInventory().removeItem(event.getPlayer().getInventory().getItemInHand());
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		if (main.crateOnKill) {
			if (event.getEntity().getKiller() != null) {
				Player player = event.getEntity().getKiller();
				if (player.hasPermission("crates.get.kill")) {
					if (player.getInventory().firstEmpty() != -1) {
						main.giveCrate(player.getInventory());
						player.sendMessage(ChatColor.GRAY + "You got a magic crate for killing " + event.getEntity().getName() + "!");
					}
				}
			}
		}
	}
}
