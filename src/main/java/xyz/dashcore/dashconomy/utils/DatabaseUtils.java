package xyz.dashcore.dashconomy.utils;

import org.spongepowered.api.entity.living.player.Player;
import xyz.dashcore.dashconomy.Main;
import xyz.dashcore.dashconomy.config.ConfigurationManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtils {
	public static void setupDefaultDatabase() {
		try {
			String tablePrefix = ConfigurationManager.getInstance().getConfig().getNode("database", "table-prefix").getString();
			String accountTable = tablePrefix + "account";

			String accountSql = "CREATE TABLE IF NOT EXISTS " + accountTable + " (" +
					"`id` INT NOT NULL AUTO_INCREMENT," +
					"`uuid` VARCHAR(36) NOT NULL," +
					"`balance` DOUBLE NOT NULL," +
					"PRIMARY KEY (`id`)," +
					"UNIQUE (`uuid`)" +
					");";

			PreparedStatement accountStmt = Main.connection.prepareStatement(accountSql);
			accountStmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void setupPlayer(Player player) {
		if(!playerExists(player)) {
			try {
				String tablePrefix = ConfigurationManager.getInstance().getConfig().getNode("database", "table-prefix").getString();
				String accountTable = tablePrefix + "account";

				String playerSql = "INSERT IGNORE INTO " + accountTable + " (id, uuid, balance) VALUES (NULL, ?, ?)";

				PreparedStatement playerStmt = Main.connection.prepareStatement(playerSql);
				playerStmt.setString(1, player.getUniqueId().toString());
				playerStmt.setDouble(2, ConfigurationManager.getInstance().getConfig().getNode("economy", "default-balance").getDouble());

				playerStmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static double getPlayerBalance(Player player) {
		try {
			String tablePrefix = ConfigurationManager.getInstance().getConfig().getNode("database", "table-prefix").getString();
			String accountTable = tablePrefix + "account";

			String balanceSql = "SELECT balance FROM " + accountTable + " WHERE uuid=? LIMIT 1";
			PreparedStatement balanceStmt = Main.connection.prepareStatement(balanceSql);
			balanceStmt.setString(1, player.getUniqueId().toString());

			ResultSet rs = balanceStmt.executeQuery();
			while(rs.next()) {
				return rs.getDouble("balance");
			}

			return 0;
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static void setPlayerBalance(Player player, Double amount) {
		try {
			String tablePrefix = ConfigurationManager.getInstance().getConfig().getNode("database", "table-prefix").getString();
			String accountTable = tablePrefix + "account";

			String updateSql = "UPDATE " + accountTable + " SET balance=? WHERE uuid=? LIMIT 1";
			PreparedStatement updateStmt = Main.connection.prepareStatement(updateSql);
			updateStmt.setDouble(1, amount);
			updateStmt.setString(2, player.getUniqueId().toString());

			updateStmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean playerExists(Player player) {
		try {
			String tablePrefix = ConfigurationManager.getInstance().getConfig().getNode("database", "table-prefix").getString();
			String accountTable = tablePrefix + "account";

			String checkSql = "SELECT COUNT(id) AS total FROM " + accountTable + " WHERE uuid=?";
			PreparedStatement checkStmt = Main.connection.prepareStatement(checkSql);
			checkStmt.setString(1, player.getUniqueId().toString());

			ResultSet rs = checkStmt.executeQuery();
			int count = 0;

			while(rs.next()) {
				count = rs.getInt("total");
			}

			if(count == 1) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}