package me.NinetyNine.poll.eventhandler;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.NinetyNine.poll.GCPoll;
import me.NinetyNine.poll.commands.GCPollCommands;
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
		ItemStack itemina = e.getCurrentItem();
		ItemMeta itemmeta = itemina.getItemMeta();
		int rawSlot = e.getRawSlot();
		InventoryView view = e.getView();

		if (item == null)
			return;

		if (inventory.getTitle().equalsIgnoreCase(GCPollCommands.question)) {
			if (item.getType().equals(Material.PAPER) && item.getItemMeta().getDisplayName() == GCPollCommands.text1
					&& item.hasItemMeta()) {
				e.setCancelled(true);
				inventory.addItem(item);
				player.closeInventory();
				GCPollUtil.sendMessage(player, "&8(&2!&8) &1Succesfully voted &l&2" + GCPollCommands.text1 + "&1!");
				return;
			}

			if (item.getType().equals(Material.PAPER) && item.getItemMeta().getDisplayName() == GCPollCommands.text2
					&& item.hasItemMeta()) {
				e.setCancelled(true);
				inventory.addItem(item);
				player.closeInventory();
				GCPollUtil.sendMessage(player, "&8(&2!&8) &1Succesfully voted &l&2" + GCPollCommands.text2 + "&1!");
				return;
			}
		}

		if (inventory.getTitle().equalsIgnoreCase(ChatColor.RED + "Choose a time!")) {
			if (item.getType().equals(Material.COAL_BLOCK)
					&& item.getItemMeta().getDisplayName() == ChatColor.GOLD + "5 minutes" && item.hasItemMeta()) {
				e.setCancelled(true);
				GCPoll.plugin.getConfig().set("whatTime", coali);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.addTime(coali);
			}

			if (item.getType().equals(Material.REDSTONE_BLOCK)
					&& item.getItemMeta().getDisplayName() == ChatColor.YELLOW + "10 minutes" && item.hasItemMeta()) {
				e.setCancelled(true);
				GCPoll.plugin.getConfig().set("whatTime", redi);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.addTime(redi);
			}

			if (item.getType().equals(Material.GOLD_BLOCK)
					&& item.getItemMeta().getDisplayName() == ChatColor.LIGHT_PURPLE + "15 minutes"
					&& item.hasItemMeta()) {
				e.setCancelled(true);
				GCPoll.plugin.getConfig().set("whatTime", goldi);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.addTime(goldi);
			}

			if (item.getType().equals(Material.IRON_BLOCK)
					&& item.getItemMeta().getDisplayName() == ChatColor.GRAY + "20 minutes" && item.hasItemMeta()) {
				e.setCancelled(true);
				GCPoll.plugin.getConfig().set("whatTime", ironi);
				GCPoll.plugin.saveConfig();
				GCPoll.plugin.reloadConfig();
				player.closeInventory();
				GCPollUtil.addTime(ironi);
			}
		}

		if (inventory.getType().equals(InventoryType.ANVIL)) {
			if (inventory instanceof AnvilInventory) {
				if (item.getType().equals(Material.PAPER)) {
					if (rawSlot == view.convertSlot(rawSlot)) {
						if (rawSlot == 2) {
							if (itemmeta != null) {
								if (itemmeta.hasDisplayName()) {
									String displayName = itemmeta.getDisplayName();
									itemmeta.setDisplayName(displayName);
									itemina.setItemMeta(itemmeta);
									inventory.addItem(itemina);
								} else
									e.setCancelled(true);
							} else
								e.setCancelled(true);
						}
					}
				}
			}
		}
	}
}