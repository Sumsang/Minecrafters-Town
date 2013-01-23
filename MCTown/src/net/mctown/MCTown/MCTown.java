package net.mctown.MCTown;

import java.util.logging.Logger;

import net.mctown.MCTown.Commands.Anvil;
import net.mctown.MCTown.Commands.Distance;
import net.mctown.MCTown.Commands.Spawnblock;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MCTown extends JavaPlugin {

	PluginDescriptionFile desc;
	Logger logger;

	public void onEnable() {
		desc = this.getDescription();
		logger = this.getLogger();
		this.getLogger().info(
				desc.getFullName() + " version " + desc.getVersion()
						+ " has been loaded!");

		// Handle commands
		getCommand("anvil").setExecutor(new Anvil(this));
		getCommand("distance").setExecutor(new Distance(this));
		getCommand("spawnblock").setExecutor(new Spawnblock(this));
	}

	public void onDisable() {
		PluginDescriptionFile desc = this.getDescription();
		this.getLogger().info(desc.getFullName() + " has been unloaded!");
	}

}
