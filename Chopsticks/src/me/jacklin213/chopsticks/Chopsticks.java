package me.jacklin213.chopsticks;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Chopsticks extends JavaPlugin {

	public Chopsticks plugin;
	PluginDescriptionFile pdfFile;
	public ChopsticksListener cl = new ChopsticksListener(this);

	public void onDisable() {
		this.pdfFile = getDescription();
		this.getLogger().info(
				this.pdfFile.getName() + " by jacklin213 is now disabled!");
	}

	public void onEnable() {
		// Variables
		PluginManager pm = getServer().getPluginManager();
		this.pdfFile = getDescription();
		// Actual startup
		this.getLogger().info(
				this.pdfFile.getName() + " Version: "
						+ this.pdfFile.getVersion()
						+ " by jacklin213 is enabled!");
		Filegen();
		pm.registerEvents(this.cl,this);
		
	}

	public void Filegen() {
		File configfile = new File(getDataFolder() + File.separator
				+ "config.yml");

		if (!configfile.exists()) {
			this.getLogger().info("Generating Config.yml.....");
			this.getConfig().options().copyDefaults(true);
			this.saveDefaultConfig();
			this.getLogger().info("Config.yml generated and saved");
		}
	}

	public boolean onCommand(CommandSender sender, Command command,
			String commandLabel, String args[]) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("This is a player only command!");
			return true;
		} else {
			Player player = (Player) sender;
			if (commandLabel.equalsIgnoreCase("chopsticks")) {
				if (sender.hasPermission("chopsticks.give")) {
					PlayerInventory inventory = player.getInventory();
					try {
						int materialid = this.getConfig().getInt("Chopstick");
						ItemStack chopsticks = new ItemStack(
								Material.getMaterial(materialid));
						inventory.addItem(new ItemStack[] { chopsticks });
						inventory.addItem(new ItemStack[] { chopsticks });
						sender.sendMessage("You have been given Chopsticks");
					} catch (NumberFormatException e) {
						this.getLogger().warning(
								this.pdfFile.getName()
										+ " Not a valid MaterialID!");
						this.getLogger()
								.warning(
										this.pdfFile.getName()
												+ " Please make sure to check the value of Chopstick: in the config.yml!");
					}
				} else {
					sender.sendMessage(ChatColor.RED
							+ "Sorry you do not have the permission to use this command!");
				}
			}

		}
		return false;
	}
}
