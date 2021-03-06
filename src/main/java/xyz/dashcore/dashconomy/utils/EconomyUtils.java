package xyz.dashcore.dashconomy.utils;

import org.spongepowered.api.entity.living.player.Player;

public class EconomyUtils {
	public EconomyUtils() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean createPlayerAccount(Player player) {
		DatabaseUtils.setupPlayer(player);

		return true;
	}

	public static boolean depositBalance(Player player, double amount) {
		double currentBalance = DatabaseUtils.getPlayerBalance(player);

		DatabaseUtils.setPlayerBalance(player, currentBalance + amount);
		return true;
	}

	public static boolean withdrawBalance(Player player, double amount) {
		double currentBalance = DatabaseUtils.getPlayerBalance(player);

		DatabaseUtils.setPlayerBalance(player, currentBalance - amount);
		return true;
	}

	public static boolean setBalance(Player player, double amount) {
		DatabaseUtils.setPlayerBalance(player, amount);
		return true;
	}

	public static double getBalance(Player player) {
		return DatabaseUtils.getPlayerBalance(player);
	}

	public static boolean hasBalance(Player player, double amount) {
		return DatabaseUtils.getPlayerBalance(player) >= amount;
	}

	public static boolean hasAccount(Player player) {
		return DatabaseUtils.playerExists(player);
	}
}
