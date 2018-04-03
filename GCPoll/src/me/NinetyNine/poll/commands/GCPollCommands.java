package me.NinetyNine.poll.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import me.NinetyNine.poll.utils.GCPollUtil;

public class GCPollCommands implements Listener, CommandExecutor {

	public static String question = "";
	public static String text1 = "";
	public static String text2 = "";
	public static String text3 = "";

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		Inventory inventory = GCPollUtil.inv;
		String eprefix = ChatColor.translateAlternateColorCodes('&', "&8(&c!&8)&c ");
		String oprefix = ChatColor.translateAlternateColorCodes('&', "&8(&2!&8)&2 ");

		if (cmd.getName().equalsIgnoreCase("poll")) {
			if (player.hasPermission("poll.poll")) {
				if (args.length == 0) {
					GCPollUtil.sendMessage(player, eprefix + "Usage: /poll vote");
					return true;
				}

				if (args[0].equalsIgnoreCase("vote")) {
					if (args.length == 1) {
						if (inventory.getContents() != null) {
							player.openInventory(inventory);
							return true;
						} else {
							GCPollUtil.sendMessage(player, eprefix + "There must be a poll ongoing to vote!");
							return true;
						}
					}
				}

				if (player.hasPermission("poll.admin")) {
					if (args[0].equalsIgnoreCase("stop")) {
						if (args.length == 1) {
							if (inventory.getContents() != null) {
								GCPollUtil.stopPoll(inventory);
								return true;
							} else {
								GCPollUtil.sendMessage(player,
										eprefix + "There must be a poll ongoing to stop the poll!");
								return true;
							}
						}
					}

					if (args[0].equalsIgnoreCase("start")) {
						if (args.length == 1) {
							if (inventory.getContents() != null) {
								GCPollUtil.sendMessage(player, eprefix + "Usage: /poll start <question>");
								return true;
							} else {

							}
						}
						String question1 = question;
						for (int i = 2; i < args.length; i++) {
							question1 += args[i] + " ";
						}
						question1.trim();
					}

					if (args[0].equalsIgnoreCase("add")) {
						if (args.length == 1) {
							if (inventory.getContents() == null) {
								GCPollUtil.sendMessage(player, eprefix + "Usage: /poll add option <one/two/three>");
								return true;
							} else {
								GCPollUtil.sendMessage(player,
										eprefix + "There are no poll ongoing! Therefore you can't add any options!");
								return true;
							}
						}
					}

					if (args[1].equalsIgnoreCase("option")) {
						if (args.length == 2) {
							if (inventory.getContents() == null) {
								GCPollUtil.sendMessage(player,
										eprefix + "Usage: /poll add option <one/two/three> <option>");
								return true;
							} else {
								GCPollUtil.sendMessage(player,
										eprefix + "There are no poll ongoing! Therefore you can't add any options!");
								return true;
							}
						}
					}

					if (args[2].equalsIgnoreCase("one")) {
						if (args.length == 3) {
							if (inventory.getContents() == null) {
								GCPollUtil.sendMessage(player, eprefix + "Usage: /poll add option one <option>");
								return true;
							} else {
								GCPollUtil.sendMessage(player,
										eprefix + "There is a poll ongoing! Therefore you cannot add any options!");
								return true;
							}
						}
						String option1 = text1;
						for (int i1 = 4; i1 < args.length; i1++) {
							option1 += args[i1] + " ";
						}
						option1.trim();
						GCPollUtil.setDisplay(player, option1);
						GCPollUtil.sendMessage(player, oprefix + "Succesfully set " + option1 + " to option one!");
						return true;
					}

					if (args[2].equalsIgnoreCase("two")) {
						if (args.length == 3) {
							if (inventory.getContents() == null) {
								GCPollUtil.sendMessage(player, eprefix + "Usage: /poll add option two <option>");
								return true;
							} else {
								GCPollUtil.sendMessage(player,
										eprefix + "There is a poll ongoing! Therefore you cannot add any options!");
								return true;
							}
						}
						String option2 = text2;
						for (int i1 = 4; i1 < args.length; i1++) {
							option2 += args[i1] + " ";
						}
						option2.trim();
						GCPollUtil.setDisplay(player, option2);
						GCPollUtil.sendMessage(player, oprefix + "Succesfully set " + option2 + " to option two!");
						return true;

					}

					if (args[2].equalsIgnoreCase("three")) {
						if (args.length == 3) {
							if (inventory.getContents() == null) {
								GCPollUtil.sendMessage(player, eprefix + "Usage: /poll add option three <option>");
								return true;
							} else {
								GCPollUtil.sendMessage(player,
										eprefix + "There is a poll ongoing! Therefore you cannot add any options!");
								return true;
							}
						}
						String option3 = text3;
						for (int i1 = 4; i1 < args.length; i1++) {
							option3 += args[i1] + " ";
						}
						option3.trim();
						GCPollUtil.setDisplay(player, option3);
						GCPollUtil.sendMessage(player, oprefix + "Succesfully set " + option3 + " to option three!");
						return true;
					}
				} else
					GCPollUtil.sendMessage(player, eprefix + "No permission.");
				return true;
			} else
				GCPollUtil.sendMessage(player, eprefix + "No permission.");
			return true;
		}
		return true;
	}
}
