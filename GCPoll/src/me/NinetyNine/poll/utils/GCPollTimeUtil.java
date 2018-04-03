package me.NinetyNine.poll.utils;

import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.poll.GCPoll;

public class GCPollTimeUtil implements Listener {

	public static void onStart() {
		new BukkitRunnable() {
			@Override
			public void run() {
				GCPollUtil.onEnd();
			}
		}.runTaskLater(GCPoll.plugin, 1200 * GCPoll.plugin.getConfig().getInt("whatTime"));
	}
}