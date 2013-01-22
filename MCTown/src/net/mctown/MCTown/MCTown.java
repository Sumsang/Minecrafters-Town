package net.mctown.MCTown;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class MCTown extends JavaPlugin {

	public void onEnable() {
		PluginDescriptionFile desc = this.getDescription();
		this.getLogger().info(
				desc.getFullName() + " version " + desc.getVersion()
						+ " has been loaded!");
	}

	public void onDisable() {
		PluginDescriptionFile desc = this.getDescription();
		this.getLogger().info(desc.getFullName() + " has been unloaded!");
	}
}
