package xyz.dashcore.dashconomy;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;

@Plugin(
		id = "dashconomy",
		name = "DashConomy",
		version = "1.0",
		description = "A simple Monument economy handler plugin.",
		url = "https://dashcore.xyz",
		authors = {
				"Dashcore Team",
				"chocoearly44"
		},
		dependencies = {
				@Dependency(id = "monument")
		}
)
public class Main {
	@Inject
	private Logger logger;

	@Listener
	public void onServerStart(GameStartedServerEvent event) {
	}
}
