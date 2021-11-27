package xyz.dashcore.dashconomy.listeners;

import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import xyz.dashcore.dashconomy.utils.DatabaseUtils;

public class PlayerJoinListener {
	@Listener
	public void clientJoinEvent(ClientConnectionEvent.Join event) {
		DatabaseUtils.setupPlayer(event.getTargetEntity());
	}
}
