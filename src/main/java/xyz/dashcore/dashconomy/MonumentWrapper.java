package xyz.dashcore.dashconomy;

import org.spongepowered.api.entity.living.player.Player;
import xyz.dashcore.dashconomy.config.ConfigurationManager;
import xyz.dashcore.dashconomy.utils.EconomyUtils;
import xyz.dashcore.monument.api.economy.Economy;
import xyz.dashcore.monument.api.economy.EconomyResponse;
import xyz.dashcore.monument.api.economy.EconomyResponse.ResponseType;

public class MonumentWrapper implements Economy {
	@Override
	public boolean isEnabled() {
		return Main.getInstance() != null;
	}

	@Override
	public String getEconomyName() {
		return "DashConomy";
	}

	@Override
	public String currencyNameSingular() {
		return ConfigurationManager.getInstance().getConfig().getNode("economy", "name-singular").getString();
	}

	@Override
	public String currencyNamePlural() {
		return ConfigurationManager.getInstance().getConfig().getNode("economy", "name-plural").getString();
	}

	@Override
	public String format(double v) {
		return String.valueOf(v);
	}

	@Override
	public boolean createPlayerAccount(Player player) {
		return EconomyUtils.createPlayerAccount(player);
	}

	@Override
	public boolean hasAccount(Player player) {
		return EconomyUtils.hasAccount(player);
	}

	@Override
	public double getBalance(Player player) {
		return EconomyUtils.getBalance(player);
	}

	@Override
	public EconomyResponse depositPlayer(Player player, double amount) {
		if(EconomyUtils.depositBalance(player, amount)) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to deposit funds.");
		}
		return new EconomyResponse(amount, getBalance(player), ResponseType.SUCCESS, "");
	}

	@Override
	public EconomyResponse withdrawPlayer(Player player, double amount) {
		if(!EconomyUtils.withdrawBalance(player, amount)) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to withdraw funds.");
		}

		return new EconomyResponse(amount, getBalance(player), ResponseType.SUCCESS, "");
	}

	@Override
	public boolean hasBalance(Player player, double amount) {
		return EconomyUtils.hasBalance(player, amount);
	}
}