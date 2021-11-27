package xyz.dashcore.dashconomy;

import com.google.inject.Inject;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import xyz.dashcore.dashconomy.config.ConfigurationManager;
import xyz.dashcore.dashconomy.listeners.PlayerJoinListener;
import xyz.dashcore.dashconomy.utils.DatabaseUtils;
import xyz.dashcore.monument.api.economy.Economy;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
	private static Main instance;
	public static Connection connection;

	private static MonumentWrapper monumentWrapper;

	public static Main getInstance() {
		return instance;
	}

	@Inject
	private Logger logger;

	@Inject
	@DefaultConfig(sharedRoot = true)
	private File configFile;

	@Inject
	@DefaultConfig(sharedRoot = true)
	ConfigurationLoader<CommentedConfigurationNode> configManager;

	@Listener
	public void onServerInitialised(GameInitializationEvent event) throws IOException {
		monumentWrapper = new MonumentWrapper();
		ConfigurationManager.getInstance().setup(configFile, configManager);

		Sponge.getServiceManager().setProvider(this, Economy.class, monumentWrapper);
		Sponge.getEventManager().registerListeners(this, new PlayerJoinListener());

		// Setup database connection
		String username = ConfigurationManager.getInstance().getConfig().getNode("database", "username").getString();
		String password = ConfigurationManager.getInstance().getConfig().getNode("database", "password").getString();

		StringBuilder url = new StringBuilder();
		url.append("jdbc:mysql://");
		url.append(ConfigurationManager.getInstance().getConfig().getNode("database", "host").getString());
		url.append(":");
		url.append(ConfigurationManager.getInstance().getConfig().getNode("database", "port").getInt());
		url.append("/");
		url.append(ConfigurationManager.getInstance().getConfig().getNode("database", "database").getString());

		try {
			connection = DriverManager.getConnection(url.toString(), username, password);
		} catch(SQLException e) {
			e.printStackTrace();
			return;
		}

		DatabaseUtils.setupDefaultDatabase();
	}
}
