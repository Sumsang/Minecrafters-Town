package net.mctown.MCTown.Commands;

import net.mctown.MCTown.MCTown;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Anvil implements CommandExecutor {

	private final MCTown ref;

	public Anvil(MCTown plugin) {
		this.ref = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		for (Player iPlayer : ref.getServer().getOnlinePlayers()) {
			String lName = iPlayer.getName();
			if (!(lName.equals(sender.getName()))) {
				Location lLoc = iPlayer.getLocation();
				lLoc.setY(lLoc.getY() + 15);
				final Block lBlock = lLoc.getBlock();
				lBlock.setTypeId(145);
				iPlayer.sendMessage(ChatColor.RED + "Watch your head! TROLOLOL");
				ref.getServer().getScheduler()
						.scheduleSyncDelayedTask(ref, new Runnable() {
							@Override
							public void run() {
								lBlock.setTypeId(0);
							}
						}, 3 * 20L);
			} 
			return true;
		}
		return false;
	}
}
