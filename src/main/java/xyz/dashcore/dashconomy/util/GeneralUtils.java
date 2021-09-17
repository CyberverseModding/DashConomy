package xyz.dashcore.dashconomy.util;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashcore.dashconomy.Main;

import java.util.logging.Logger;

public class GeneralUtils {
	public static Player toPlayer(CommandSender commandSender) {
		return commandSender.getServer().getPlayer(commandSender.getName());
	}

	@SuppressWarnings("deprecation")
	public static Player toPlayer(String name) {
		return Bukkit.getOfflinePlayer(name).getPlayer();
	}

	public static Player toPlayer(OfflinePlayer offlinePlayer) {
		return offlinePlayer.getPlayer();
	}

	public static void disablePluginWithError(Logger log, String error) {
		log.severe("\033[0;31mDashConomy cannot be enabled. " + error + "\033[0m");
		Bukkit.getPluginManager().disablePlugin(Main.getInstance());
	}
}
