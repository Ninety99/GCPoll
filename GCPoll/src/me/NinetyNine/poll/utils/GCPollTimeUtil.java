package me.NinetyNine.poll.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.poll.GCPoll;

public class GCPollTimeUtil implements Listener {

	public static GCPoll plugin;

	public static void onStart(Player player) {
		plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				GCPollUtil.onEnd();
			}
		}, 20 * plugin.getConfig().getInt("whatTime"));
	}
}