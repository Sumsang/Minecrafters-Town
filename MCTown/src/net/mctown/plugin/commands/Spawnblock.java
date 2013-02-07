/**
 * This class handles the /spawnblock command.
 *
 * @author Sumsang
 */
package net.mctown.plugin.commands;

import net.mctown.plugin.MCTown;

import org.bukkit.ChatColor;
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
		ref = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			player = (Player) sender;
			this.getBlock();
			if (this.setBlock(Material.GLASS)) {
				player.sendMessage("A glass block was created under your feet!");
			} else {
				player.sendMessage(ChatColor.RED
						+ "The block under your feet is not empty!");
			}
			this.setTimer(block, 60);
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
	private boolean setBlock(Material material) {
		if (block.isEmpty()) {
			block.setType(material);
			return true;
		} else
			return false;
	}

	private void setTimer(Block pBlock, int delay) {
		final Block lBlock = pBlock;
		ref.getServer().getScheduler()
				.scheduleSyncDelayedTask(ref, new Runnable() {
					@Override
					public void run() {
						lBlock.setTypeId(0);
					}
				}, delay * 20L);
	}
}
