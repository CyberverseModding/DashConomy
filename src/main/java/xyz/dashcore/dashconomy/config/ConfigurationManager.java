package xyz.dashcore.dashconomy.config;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager {
	private static ConfigurationManager instance = new ConfigurationManager();

	private ConfigurationLoader<CommentedConfigurationNode> configLoader;
	private CommentedConfigurationNode config;

	public void setup(File configFile, ConfigurationLoader<CommentedConfigurationNode> configLoader) {
		this.configLoader = configLoader;

		if(!configFile.exists()) {
			try {
				configFile.createNewFile();
				loadConfig();

				config.getNode("chat-prefix").setComment("Prefix for plugin messages").setValue("&r&f[&6&lAnvil&2&lConomy&r&f]");

				CommentedConfigurationNode databaseRoot = config.getNode("database");
				databaseRoot.getNode("host").setComment("Hostname or ip (without port)").setValue("localhost");
				databaseRoot.getNode("port").setValue(3306);
				databaseRoot.getNode("database").setValue("dashconomy");
				databaseRoot.getNode("username").setValue("dashconomy");
				databaseRoot.getNode("password").setValue("money");
				databaseRoot.getNode("table-prefix").setComment("Leave as-is if you don't know what it does").setValue("dc_");

				CommentedConfigurationNode economyRoot = config.getNode("economy").setComment("Economy config");
				economyRoot.getNode("name-singular").setComment("Currency name in singular (e.g. Dollar)").setValue("Credits");
				economyRoot.getNode("name-plural").setComment("Currency name in plural (e.g. Dollars)").setValue("Credits");
				economyRoot.getNode("default-balance").setComment("Start balance for new players").setValue(2500);

				saveConfig();
			} catch(IOException e) {
				e.printStackTrace();
			}
		} else {
			loadConfig();
		}
	}

	public void saveConfig() {
		try {
			configLoader.save(config);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void loadConfig() {
		try {
			config = configLoader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public CommentedConfigurationNode getConfig() {
		return config;
	}

	public static ConfigurationManager getInstance() {
		return instance;
	}
}
