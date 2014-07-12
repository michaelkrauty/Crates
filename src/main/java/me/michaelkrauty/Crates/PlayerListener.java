package me.michaelkrauty.Crates;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

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
		// TODO
	}
}
