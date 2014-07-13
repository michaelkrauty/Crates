package me.michaelkrauty.Crates;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/**
 * Created on 7/12/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	public static Main main;

	public void onEnable() {
		main = this;
		getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		getServer().getPluginCommand("crate").setExecutor(new CratesCommand(this));
		if (!getDataFolder().exists())
			getDataFolder().mkdir();
		File configFile = new File(getDataFolder() + "/config.yml");
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
				InputStream input = main.getResource("config.yml");
				OutputStream output = new FileOutputStream(configFile);
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = input.read(buffer)) > 0) {
					output.write(buffer, 0, bytesRead);
				}
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public static void giveCrate(Inventory inventory) {
		ItemStack item = new ItemStack(Material.CHEST, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Magic Crate");
		meta.setLore(Arrays.asList("A Magical Storage Box"));
		item.setItemMeta(meta);
		inventory.addItem(item);
	}
}
