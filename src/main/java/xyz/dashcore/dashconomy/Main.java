package xyz.dashcore.dashconomy;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.dashcore.dashconomy.listeners.PlayerJoinListener;
import xyz.dashcore.dashconomy.util.DatabaseUtils;
import xyz.dashcore.dashconomy.util.GeneralUtils;
import xyz.dashcore.dashconomy.util.VaultUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import static xyz.dashcore.dashconomy.util.ConstantsUtils.*;

public final class Main extends JavaPlugin {
	private static final Logger log = Logger.getLogger("Minecraft");
	public static FileConfiguration config;
	public static Connection connection;

	private static VaultUtils vaultUtils;
	private static Main instance;

	public static Main getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		config = getConfig();
		instance = this;

		setupDefaultConfig();

		// Register Vault
		if(this.getServer().getPluginManager().getPlugin("Vault") == null) {
			GeneralUtils.disablePluginWithError(log, "Vault plugin appears to be missing!");
			return;
		}

		vaultUtils = new VaultUtils();
		Bukkit.getServer().getServicesManager().register(Economy.class, vaultUtils, this, ServicePriority.Highest);

		// Setup database connection
		String username = config.getString("database.username");
		String password = config.getString("database.password");

		StringBuilder url = new StringBuilder();
		url.append("jdbc:mysql://");
		url.append(config.getString("database.host"));
		url.append(":");
		url.append(config.getInt("database.port"));
		url.append("/");
		url.append(config.getString("database.database"));

		try {
			connection = DriverManager.getConnection(url.toString(), username, password);
		} catch(SQLException e) {
			GeneralUtils.disablePluginWithError(log, "MySQL connection cannot be established!");
			return;
		}

		DatabaseUtils.setupDefaultDatabase();

		// Register events
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

		log.info("Plugin DashConomy enabled.");
	}

	@Override
	public void onDisable() {
		try {
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}

		log.info("Plugin DashConomy disabled.");
	}

	public void setupDefaultConfig() {
		// Plugin
		config.addDefault(CONF_PREFIX, "&r&f[&6&lDash&f&lConomy&r&f]");

		// Currency
		config.addDefault(CONF_CURRENCY_NAME_SINGULAR, "Credit");
		config.addDefault(CONF_CURRENCY_NAME_PLURAL, "Credits");

		// Database
		config.addDefault(CONF_DB_HOST, "localhost");
		config.addDefault(CONF_DB_PORT, 3306);
		config.addDefault(CONF_DB_USERNAME, "DashConomy");
		config.addDefault(CONF_DB_PASSWORD, "password");
		config.addDefault(CONF_DB_DATABASE, "DashConomy");
		config.addDefault(CONF_DB_PREFIX, "dc_");

		// Balance
		config.addDefault(CONF_BALANCE_DEFAULT, 2500.0);

		config.options().copyDefaults(true);
		saveConfig();
	}
}