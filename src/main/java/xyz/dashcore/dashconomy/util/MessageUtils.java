package xyz.dashcore.dashconomy.util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import xyz.dashcore.dashconomy.Main;

public class MessageUtils {
	public static void sendMessage(String message, Player player) {
		send(message, player);
	}

	public static void sendMessage(String message, CommandSender sender) {
		send(message, sender.getServer().getPlayer(sender.getName()));
	}

	public static void sendNoPermission(CommandSender sender) {
		send("&4You don't have permission to use that command.", sender.getServer().getPlayer(sender.getName()));
	}

	private static void send(String rawMessage, Player player) {
		StringBuilder message = new StringBuilder();
		message.append(Main.config.getString(ConstantsUtils.CONF_PREFIX));
		message.append(" ");
		message.append(rawMessage);

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message.toString()));
	}
}
