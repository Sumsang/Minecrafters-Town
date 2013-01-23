package net.mctown.MCTown.Commands;

import net.mctown.MCTown.MCTown;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LockTime implements CommandExecutor {
	private final MCTown ref;
	private int taskID;
	private boolean locked = false;
	Player player;

	public LockTime(MCTown plugin) {
		ref = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		player = (Player) sender;
		if (args.length == 0) {
			if (locked) {
				this.unlock();
				sender.sendMessage("The time was unlocked!");
			} else {
				sender.sendMessage("The time is already unlocked!");
			}
			return true;
		} else if (args.length > 1) {
			sender.sendMessage(ChatColor.RED + "Too many arguements");
		} else if (args[0].equalsIgnoreCase("day")) {
			if (locked) {
				sender.sendMessage("Please unlock the time first!");
			} else {
				this.lockDay();
				sender.sendMessage("Day-time was locked!");
			}
			return true;
		} else if (args[0].equalsIgnoreCase("night")) {
			if (locked) {
				sender.sendMessage("Please unlock the time first!");
			} else {
				this.lockNight();
				sender.sendMessage("Night-time was locked!");
			}
			return true;
		} else {
			sender.sendMessage(ChatColor.RED + "Invalid arguement!");
		}
		return false;
	}

	private void lockDay() {
		final World lWorld = player.getWorld();
		taskID = ref.getServer().getScheduler()
				.scheduleSyncRepeatingTask(ref, new Runnable() {
					@Override
					public void run() {
						lWorld.setTime(6000);
					}
				}, 0L, 15 * 20L);
		locked = true;
	}

	private void lockNight() {
		final World lWorld = player.getWorld();
		taskID = ref.getServer().getScheduler()
				.scheduleSyncRepeatingTask(ref, new Runnable() {
					@Override
					public void run() {
						lWorld.setTime(18000);
					}
				}, 0L, 15 * 20L);
		locked = true;
	}

	private void unlock() {
		ref.getServer().getScheduler().cancelTask(taskID);
		taskID = 0;
		locked = false;
	}
}
