package xyz.dashcore.dashconomy.util;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import xyz.dashcore.dashconomy.Main;

import java.util.List;

import static xyz.dashcore.dashconomy.util.GeneralUtils.toPlayer;

public class VaultUtils implements Economy {
	/* GENERAL SETUP */
	@Override
	public boolean isEnabled() {
		return Main.getInstance() != null;
	}

	@Override
	public String currencyNameSingular() {
		return "Credit";
	}

	@Override
	public String currencyNamePlural() {
		return "Credits";
	}

	@Override
	public String format(double v) {
		return String.valueOf(v);
	}

	@Override
	public int fractionalDigits() {
		return -1;
	}

	@Override
	public String getName() {
		return "DashConomy";
	}
	/* GENERAL SETUP */

	/* PLAYER ACCOUNT CREATION */
	@Override
	public boolean createPlayerAccount(String name) {
		return EconomyUtils.createPlayerAccount(GeneralUtils.toPlayer(name));
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
		return EconomyUtils.createPlayerAccount(GeneralUtils.toPlayer(offlinePlayer));
	}

	@Override
	public boolean createPlayerAccount(String name, String world) {
		return EconomyUtils.createPlayerAccount(GeneralUtils.toPlayer(name));
	}

	@Override
	public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String world) {
		return EconomyUtils.createPlayerAccount(GeneralUtils.toPlayer(offlinePlayer));
	}
	/* PLAYER ACCOUNT CREATION */

	/* PLAYER DEPOSIT */
	@Override
	public EconomyResponse depositPlayer(String name, double amount) {
		return deposit(GeneralUtils.toPlayer(name), amount);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
		return deposit(GeneralUtils.toPlayer(offlinePlayer), amount);
	}

	@Override
	public EconomyResponse depositPlayer(String name, String world, double amount) {
		return deposit(GeneralUtils.toPlayer(name), amount);
	}

	@Override
	public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
		return deposit(GeneralUtils.toPlayer(offlinePlayer), amount);
	}

	private EconomyResponse deposit(Player player, double amount) {
		if(EconomyUtils.depositBalance(player, amount)) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to deposit funds.");
		}
		return new EconomyResponse(amount, getBalance(player), ResponseType.SUCCESS, "");
	}
	/* PLAYER DEPOSIT */

	/* PLAYER WITHDRAWAL */
	@Override
	public EconomyResponse withdrawPlayer(String name, double amount) {
		return withdraw(GeneralUtils.toPlayer(name), amount);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
		return withdraw(GeneralUtils.toPlayer(offlinePlayer), amount);
	}

	@Override
	public EconomyResponse withdrawPlayer(String name, String world, double amount) {
		return withdraw(GeneralUtils.toPlayer(name), amount);
	}

	@Override
	public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
		return withdraw(GeneralUtils.toPlayer(offlinePlayer), amount);
	}

	private EconomyResponse withdraw(Player player, double amount) {
		if(!EconomyUtils.withdrawBalance(player, amount)) {
			return new EconomyResponse(0, 0, ResponseType.FAILURE, "Failed to withdraw funds.");
		}

		return new EconomyResponse(amount, getBalance(player), ResponseType.SUCCESS, "");
	}
	/* PLAYER WITHDRAWAL */

	/* PLAYER BALANCE */
	@Override
	public double getBalance(String name) {
		return getBalance(GeneralUtils.toPlayer(name));
	}

	@Override
	public double getBalance(OfflinePlayer offlinePlayer) {
		return getBalance(GeneralUtils.toPlayer(offlinePlayer));
	}

	@Override
	public double getBalance(String name, String world) {
		return getBalance(GeneralUtils.toPlayer(name));
	}

	@Override
	public double getBalance(OfflinePlayer offlinePlayer, String world) {
		return getBalance(GeneralUtils.toPlayer(offlinePlayer));
	}

	private double getBalance(Player player) {
		return EconomyUtils.getBalance(player);
	}
	/* PLAYER BALANCE */

	/* PLAYER HAS REQUIRED BALANCE */
	@Override
	public boolean has(String name, double amount) {
		return has(GeneralUtils.toPlayer(name), amount);
	}

	@Override
	public boolean has(OfflinePlayer offlinePlayer, double amount) {
		return has(GeneralUtils.toPlayer(offlinePlayer), amount);
	}

	@Override
	public boolean has(String name, String world, double amount) {
		return has(GeneralUtils.toPlayer(name), amount);
	}

	@Override
	public boolean has(OfflinePlayer offlinePlayer, String world, double amount) {
		return has(GeneralUtils.toPlayer(offlinePlayer), amount);
	}

	private boolean has(Player player, double amount) {
		return EconomyUtils.hasBalance(player, amount);
	}
	/* PLAYER HAS REQUIRED BALANCE */

	/* PLAYER HAS ACCOUNT */
	@Override
	public boolean hasAccount(String name) {
		return hasAccount(GeneralUtils.toPlayer(name));
	}

	@Override
	public boolean hasAccount(OfflinePlayer offlinePlayer) {
		return hasAccount(GeneralUtils.toPlayer(offlinePlayer));
	}

	@Override
	public boolean hasAccount(String name, String world) {
		return hasAccount(GeneralUtils.toPlayer(name));
	}

	@Override
	public boolean hasAccount(OfflinePlayer offlinePlayer, String world) {
		return hasAccount(GeneralUtils.toPlayer(offlinePlayer));
	}

	private boolean hasAccount(Player player) {
		return EconomyUtils.hasAccount(player);
	}
	/* PLAYER HAS ACCOUNT */

	/* BANK SETUP */
	@Override
	public boolean hasBankSupport() {
		return false;
	}

	@Override
	public List<String> getBanks() {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankBalance(String arg0) {
		return null;
	}

	@Override
	public EconomyResponse bankDeposit(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankHas(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse bankWithdraw(String arg0, double arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, String arg1) {
		return null;
	}

	@Override
	public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
		return null;
	}

	@Override
	public EconomyResponse deleteBank(String arg0) {
		return null;
	}
	/* BANK SETUP */
}
