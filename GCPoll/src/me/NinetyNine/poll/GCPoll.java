package me.NinetyNine.poll;

import org.bukkit.plugin.java.JavaPlugin; 

import me.NinetyNine.poll.commands.GCPollCommands;
import me.NinetyNine.poll.eventhandler.GCPollEventHandler;
import me.NinetyNine.poll.utils.GCPollTimeUtil;
import me.NinetyNine.poll.utils.GCPollUtil;

public class GCPoll extends JavaPlugin {

	/*
	 * Created 4/1/2018.
	 * 
	 * Last edited: 3rd of April 2018
	 * 
	 * TODO: 
	 * = If possible, make it an anvil inventory(in making the options).
	 *   > Get the item meta of currentitem.
	 *   > Set and add in the inventory
	 *   (removed, can add later)
	 * = Fix bugs
	 */

	public static GCPoll plugin;

	@Override
	public void onEnable() {
		plugin = this;

		getServer().getPluginManager().registerEvents(new GCPollEventHandler(), this);
		getServer().getPluginManager().registerEvents(new GCPollTimeUtil(), this);
		getServer().getPluginManager().registerEvents(new GCPollUtil(), this);
		getCommand("poll").setExecutor(new GCPollCommands());

		GCPollUtil.sendConsoleMsg("Getting config...");
		plugin.getConfig().set("whatQuestion", "");
		plugin.getConfig().set("whatOption1", "");
		plugin.getConfig().set("whatOption2", "");
		plugin.getConfig().set("whatOption3", "");
		plugin.getConfig().set("whatTitle", "");
		plugin.getConfig().set("whatTime", "");
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		GCPollUtil.sendConsoleMsg("Enabled!");
	}

	@Override
	public void onDisable() {
		GCPollUtil.sendConsoleMsg("Saving config...");
		saveConfig();
		reloadConfig();
		GCPollUtil.sendConsoleMsg("Disabled!");
	}
}
