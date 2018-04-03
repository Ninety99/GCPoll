package me.NinetyNine.poll.utils;

import static org.bukkit.ChatColor.DARK_GRAY;
import static org.bukkit.ChatColor.DARK_GREEN;
import static org.bukkit.ChatColor.DARK_PURPLE;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.GRAY;
import static org.bukkit.ChatColor.GREEN;
import static org.bukkit.ChatColor.LIGHT_PURPLE;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.YELLOW;
import static org.bukkit.ChatColor.translateAlternateColorCodes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.poll.GCPoll;
import me.NinetyNine.poll.commands.GCPollCommands;

public class GCPollUtil implements Listener {

	public static String invTitle = GCPollCommands.question1;
	
	//inv is going to be the getter of the options
	public static Inventory inv = Bukkit.createInventory(null, 9, invTitle);
	public static String inventoryTitle = inv.getTitle();
	
	public static String invvT = GCPoll.plugin.getConfig().getString("whatTitle");
	//create1more is going to be the one who sets the title and meet the other title with an event handler
	public static Inventory create1more = Bukkit.createInventory(null, 9, invvT);

	public static ItemStack paper1 = new ItemStack(Material.PAPER);

	public static void setDisplay(Player player, String displayName) {
		Inventory inv1 = inv;

		ItemStack paper = paper1;
		ItemMeta papermeta = paper.getItemMeta();
		papermeta.setDisplayName(displayName);
		paper.setItemMeta(papermeta);

		inv1.addItem(paper);

		player.openInventory(inv1);
	}

	public static void startAnnouncer(String question) {
		Bukkit.getServer()
				.broadcastMessage(DARK_GRAY + "[" + DARK_GREEN + "POLL" + DARK_GRAY + "] " + GREEN
						+ "Do /poll vote to vote on the poll! " + GRAY + "(" + DARK_PURPLE + "for "
						+ GCPoll.plugin.getConfig().getInt("whatTime") + " minutes!" + GRAY + ")\n" + LIGHT_PURPLE
						+ "Question: " + RED + question);
	}

	public static void stopAnnouncer() {
		Bukkit.getServer().broadcastMessage(DARK_GRAY + "[" + DARK_GREEN + "POLL" + DARK_GRAY + "] " + RED
				+ "The current poll has been stopped! Therefore no result will be chosen");
	}

	public static void endAnnouncer(String message) {
		Bukkit.getServer().broadcastMessage(DARK_GRAY + "[" + DARK_GREEN + "POLL" + DARK_GRAY + "] " + RED
				+ "The current poll has ended!\n" + GREEN + message);
	}

	public static void addTime(int time) {
		GCPoll.plugin.getConfig().set("whatTime", time);
	}

	public static void onEnd() {
		Inventory inve = inv;

		int firstItem = inve.getItem(0).getAmount();
		int secondItem = inve.getItem(1).getAmount();
		int thirdItem = inve.getItem(2).getAmount();

		if (firstItem > secondItem && firstItem > thirdItem) {
			endAnnouncer(GCPollCommands.text1 + " has been picked!");
			inve.clear();
			return;
		}

		if (secondItem > firstItem && secondItem > thirdItem) {
			endAnnouncer(GCPollCommands.text2 + " has been picked!");
			inve.clear();
			return;
		}

		if (thirdItem > firstItem && thirdItem > secondItem) {
			endAnnouncer(GCPollCommands.text3 + " has been picked!");
			inve.clear();
			return;
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

		inventory.setItem(1, coal);
		inventory.setItem(3, redstone);
		inventory.setItem(5, gold);
		inventory.setItem(7, iron);

		player.openInventory(inventory);
	}

	/*
	 * public static void openAnvInv(Player player) { Inventory anvilinv =
	 * Bukkit.createInventory(null, InventoryType.ANVIL);
	 * 
	 * ItemStack paperr = paper1; ItemMeta paperrmeta = paperr.getItemMeta();
	 * paperrmeta.setDisplayName("Rename me as options!");
	 * paperr.setItemMeta(paperrmeta);
	 * 
	 * anvilinv.setItem(1, paperr); player.giveExpLevels(1);
	 * 
	 * player.openInventory(anvilinv); } // GCPollUtil.openAnvInv(player);
	 */

	public static void sendMessage(Player player, String message) {
		player.sendMessage(translateAlternateColorCodes('&', message));
	}

	public static void sendConsoleMsg(String message) {
		Bukkit.getServer().getLogger().info("[GCPoll] " + message);
	}

	public static void startPoll(Player player) {
		GCPollTimeUtil.onStart();
		openTime(player);
	}

	public static void stopPoll(Inventory inventory) {
		inventory.clear();
		stopAnnouncer();
	}
	
	public static void playerOpenInventory(Player player, String titleName) {
		Inventory c1m = create1more;
		ItemStack[] allIn = inv.getContents();
		c1m.setContents(allIn);
		setStrings("whatTitle", titleName);
		
		player.openInventory(c1m);
	}
	
	public static void setStrings(String string, String object) {
		GCPoll.plugin.getConfig().set(string, object);
		GCPoll.plugin.saveConfig();
		GCPoll.plugin.reloadConfig();
	}
}