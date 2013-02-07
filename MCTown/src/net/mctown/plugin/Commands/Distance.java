package net.mctown.plugin.Commands;

import net.mctown.plugin.MCTown;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Distance implements CommandExecutor {

	private final MCTown ref;
	private Player player;
	private Location playerLoc, targetLoc;
	private int distance;
	private String suffix;

	public Distance(MCTown plugin) {
		ref = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			player = (Player) sender;
			playerLoc = player.getEyeLocation();
			targetLoc = player.getTargetBlock(null, 1000).getLocation();
			this.measure();
			this.setSuffix();
			player.sendMessage("Distance: " + distance + " " + suffix);
			return true;
		} else {
			sender.sendMessage("You must be a player!");
		}
		return false;
	}

	// Get the location of the targeted block
	private void measure() {
		distance = ((int) playerLoc.distance(targetLoc));
	}

	// Singular or plural of "blocks"
	private void setSuffix() {
		if (distance == 1) {
			suffix = "block";
		} else {
			suffix = "blocks";
		}
	}
}