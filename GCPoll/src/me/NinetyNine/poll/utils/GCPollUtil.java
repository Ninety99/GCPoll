package me.NinetyNine.poll.utils;

import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.DARK_PURPLE;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.LIGHT_PURPLE;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.ChatColor.translateAlternateColorCodes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.poll.GCPoll;
import me.NinetyNine.poll.commands.GCPollCommands;

public class GCPollUtil {

	public static String invTitle = GCPollCommands.question;
	public static Inventory inv = Bukkit.createInventory(null, 9, GREEN + invTitle);

	public static ItemStack paper1 = new ItemStack(Material.PAPER);
	//public static ItemStack paper2 = new ItemStack(Material.PAPER);
	//public static ItemStack paper3 = new ItemStack(Material.PAPER);

	public static void setDisplay1(Player player, String displayName) {
		Inventory inv1 = inv;

		ItemStack paper = paper1;
		ItemMeta papermeta = paper.getItemMeta();
		papermeta.setDisplayName(displayName);
		paper.setItemMeta(papermeta);

		inv1.addItem(paper);

		player.openInventory(inv1);
	}

	public static void startAnnouncer(String question, int timee) {
		Bukkit.getServer()
				.broadcastMessage(DARK_GRAY + "[" + DARK_GREEN + "POLL" + DARK_GRAY + "] " + GREEN
						+ "Do /poll vote to vote on the poll!" + WHITE + "(" + DARK_PURPLE
						+ GCPoll.plugin.getConfig().getInt("whatTime") + " minutes!" + WHITE + ")\n" + LIGHT_PURPLE
						+ "Question: " + RED + question);
	}

	public static void addTime(int timee) {
		GCPoll.plugin.getConfig().set("whatTime", timee);
	}

	public static void stopAnnouncer() {
		Bukkit.getServer().broadcastMessage(DARK_GRAY + "[" + DARK_GREEN + "POLL" + DARK_GRAY + "] " + RED
				+ "The current poll has been stopped! Therefore no result is chosen");
	}

	public static void endAnnouncer(String message) {
		Bukkit.getServer().broadcastMessage(DARK_GRAY + "[" + DARK_GREEN + "POLL" + DARK_GRAY + "] " + RED
				+ "The current poll has ended!\n" + GREEN + message);
	}

	public static void onEnd() {
		int firstItem = inv.getItem(0).getAmount();
		int secondItem = inv.getItem(1).getAmount();
		int thirdItem = inv.getItem(2).getAmount();

		if (firstItem > secondItem) {
			if (firstItem > thirdItem) {
				endAnnouncer(GCPollCommands.text1 + " has been picked!");
				return;
			}
		}

		if (secondItem > firstItem) {
			if (secondItem > thirdItem) {
				endAnnouncer(GCPollCommands.text2 + " has been picked!");
				return;
			}
		}

		if (thirdItem > firstItem) {
			if (thirdItem > secondItem) {
				endAnnouncer(GCPollCommands.text3 + " has been picked!");
				return;
			}
		}
	}

	public static void openTime(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, RED + "Choose a time!");

		ItemStack coal = new ItemStack(Material.COAL_BLOCK, 1);
		ItemMeta coalmeta = coal.getItemMeta();
		coalmeta.setDisplayName(GOLD + "5 minutes");
		coal.setItemMeta(coalmeta);

		ItemStack redstone = new ItemStack(Material.REDSTONE_BLOCK, 1);
		ItemMeta redstonemeta = redstone.getItemMeta();
		redstonemeta.setDisplayName(YELLOW + "10 minutes");
		redstone.setItemMeta(redstonemeta);

		ItemStack gold = new ItemStack(Material.GOLD_BLOCK, 1);
		ItemMeta goldmeta = gold.getItemMeta();
		goldmeta.setDisplayName(LIGHT_PURPLE + "15 minutes");
		gold.setItemMeta(goldmeta);

		ItemStack iron = new ItemStack(Material.IRON_BLOCK, 1);
		ItemMeta ironmeta = iron.getItemMeta();
		ironmeta.setDisplayName(GRAY + "20 minutes");
		iron.setItemMeta(ironmeta);

		inventory.setItem(2, coal);
		inventory.setItem(4, redstone);
		inventory.setItem(6, gold);
		inventory.setItem(8, iron);

		player.openInventory(inventory);
	}

	public static void openAnvInv(Player player) {
		Inventory anvilinv = Bukkit.createInventory(null, InventoryType.ANVIL);

		ItemStack paperr = new ItemStack(Material.PAPER, 1);
		ItemMeta paperrmeta = paperr.getItemMeta();
		paperrmeta.setDisplayName("Rename me as options!");
		paperr.setItemMeta(paperrmeta);

		anvilinv.setItem(0, paperr);
		player.giveExpLevels(1);

		player.openInventory(anvilinv);
	}

	public static void sendMessage(Player player, String message) {
		player.sendMessage(translateAlternateColorCodes('&', message));
	}

	public static void sendConsoleMsg(String message) {
		Bukkit.getServer().getLogger().info("[GCPoll] " + message);
	}
}
