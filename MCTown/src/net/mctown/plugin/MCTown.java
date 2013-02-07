/**
 * Plugin main class
 *
 * @author Sumsang
 */
package net.mctown.plugin;

import java.util.logging.Logger;

import net.mctown.plugin.commands.Anvil;
import net.mctown.plugin.commands.Distance;
import net.mctown.plugin.commands.LockTime;
import net.mctown.plugin.commands.Spawnblock;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MCTown extends JavaPlugin {

	PluginDescriptionFile desc;
	Logger logger;

	public void onEnable() {
		desc = this.getDescription();
		logger = this.getLogger();
		this.getLogger().info(desc.getFullName() + " has been loaded!");

		// Handle commands
		getCommand("anvil").setExecutor(new Anvil(this));
		getCommand("distance").setExecutor(new Distance(this));
		getCommand("spawnblock").setExecutor(new Spawnblock(this));
		getCommand("lock").setExecutor(new LockTime(this));
	}

	public void onDisable() {
		PluginDescriptionFile desc = this.getDescription();
		this.getLogger().info(desc.getFullName() + " has been unloaded!");
	}

}
