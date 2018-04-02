package me.NinetyNine.poll.utils;

import org.bukkit.event.Listener;

import me.NinetyNine.poll.GCPoll;

public class GCPollTimeUtil implements Listener {

	public static void onStart() {
		GCPoll.plugin.getServer().getScheduler().scheduleSyncDelayedTask(GCPoll.plugin, new Runnable() {
			public void run() {
				GCPollUtil.onEnd();
			}
		}, 20 * GCPoll.plugin.getConfig().getInt("whatTime"));
	}
}