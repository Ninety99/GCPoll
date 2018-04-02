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
	 * Last edited: 2nd of April 2018
	 */

	public static GCPoll plugin;

	@Override
	public void onEnable() {
		plugin = this;

		getServer().getPluginManager().registerEvents(new GCPollEventHandler(), this);
		getServer().getPluginManager().registerEvents(new GCPollTimeUtil(), this);
		getCommand("poll").setExecutor(new GCPollCommands());

		GCPollUtil.sendConsoleMsg("Getting config...");
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
