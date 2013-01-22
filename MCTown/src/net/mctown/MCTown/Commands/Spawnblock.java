package net.mctown.MCTown.Commands;

import net.mctown.MCTown.MCTown;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawnblock implements CommandExecutor {
	private final MCTown ref;
	Player player;
	Block block; // Represents block location

	public Spawnblock(MCTown plugin) {
		this.ref = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			player = (Player) sender;
			this.getBlock();
			this.setBlock(Material.GLASS);
			this.setTimer(60);
			return true;
		} else {
			sender.sendMessage("You must be a player!");
		}
		return false;
	}

	// Get the block directly under the players feet
	private void getBlock() {
		Location lLoc;
		lLoc = player.getLocation();
		lLoc.setY(lLoc.getY() - 1);
		block = lLoc.getBlock();
	}

	// If block is air, a glass block will be set
	private void setBlock(Material material) {
		if (block.isEmpty()) {
			block.setType(material);
		}
	}

	private void setTimer(int delay) {
		ref.getServer().getScheduler()
				.scheduleSyncDelayedTask(ref, new Runnable() {
					@Override
					public void run() {
						block.setTypeId(0);
					}
				}, delay * 20L);
	}
}
