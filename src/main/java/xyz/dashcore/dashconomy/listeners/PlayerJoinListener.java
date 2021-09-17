package xyz.dashcore.dashconomy.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import xyz.dashcore.dashconomy.util.DatabaseUtils;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		DatabaseUtils.setupPlayer(event.getPlayer());
	}
}
