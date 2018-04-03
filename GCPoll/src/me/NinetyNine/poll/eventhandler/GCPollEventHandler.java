package me.NinetyNine.poll.eventhandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.NinetyNine.poll.GCPoll;
import me.NinetyNine.poll.commands.GCPollCommands;
import me.NinetyNine.poll.utils.GCPollPUtils;
import me.NinetyNine.poll.utils.GCPollUtil;

public class GCPollEventHandler implements Listener {

	public static int coali = 5;
	public static int redi = 10;
	public static int goldi = 15;
	public static int ironi = 20;

	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		Inventory inventory = e.getInventory();
		ItemStack item = e.getCurrentItem();
		String invTitle = GCPoll.plugin.getConfig().getString("whatTitle");

		if (item == null || item.getType().equals(Material.AIR))
			return;

		if (inventory.getTitle().equalsIgnoreCase(invTitle)) {
			if (item.getItemMeta().getDisplayName().equals(GCPollCommands.text1) && item.hasItemMeta()) {
				inventory.addItem(item);
				player.closeInventory();
				GCPollUtil.sendMessage(player, "&8(&2!&8) &1Succesfully voted &l&2" + GCPollCommands.text1 + "&1!");
				GCPollPUtils.sendActionBar(player, "§1Succesfully voted §l§2" + GCPollCommands.text1 + "§1!");
			}

			if (item.getItemMeta().getDisplayName() == GCPollCommands.text2 && item.hasItemMeta()) {
				inventory.addItem(item);
				player.closeInventory();
				GCPollUtil.sendMessage(player, "&8(&2!&8) &1Succesfully voted &l&2" + GCPollCommands.text2 + "&1!");
				GCPollPUtils.sendActionBar(player, "§1Succesfully voted §l§2" + GCPollCommands.text2 + "§1!");
				return;
			}

			if (item.getItemMeta().getDisplayName() == GCPollCommands.text3 && item.hasItemMeta()) {
				inventory.addItem(item);
				player.closeInventory();
				GCPollUtil.sendMessage(player, "&8(&2!&8) &1Succesfully voted &l&2" + GCPollCommands.text3 + "&1!");
				GCPollPUtils.sendActionBar(player, "§1Succesfully voted §l§2" + GCPollCommands.text3 + "§1!");
				return;
			}
		}

		if (inventory.getTitle().equalsIgnoreCase(ChatColor.RED + "Choose a time!")) {
			if (item.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "5 minutes") && item.hasItemMeta()) {
				GCPollUtil.addTime(coali);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.startAnnouncer(GCPollCommands.question1);
			}

			if (item.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "10 minutes") && item.hasItemMeta()) {
				GCPollUtil.addTime(redi);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.startAnnouncer(GCPollCommands.question1);
			}

			if (item.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "15 minutes")
					&& item.hasItemMeta()) {
				GCPollUtil.addTime(goldi);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.startAnnouncer(GCPollCommands.question1);
			}

			if (item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "20 minutes") && item.hasItemMeta()) {
				GCPollUtil.addTime(ironi);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.startAnnouncer(GCPollCommands.question1);
			}
		}
	}
}