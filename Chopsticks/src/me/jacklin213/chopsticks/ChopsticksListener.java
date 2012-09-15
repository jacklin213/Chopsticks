package me.jacklin213.chopsticks;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ChopsticksListener implements Listener {
	Chopsticks plugin;

	public ChopsticksListener(Chopsticks instance) {
		plugin = instance;
	}

	@EventHandler
	public void onEat(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if ((event.getAction() == Action.RIGHT_CLICK_BLOCK)
				|| (event.getAction() == Action.RIGHT_CLICK_AIR)) {
			try {
				int materialid = plugin.getConfig().getInt("Chopstick");
			} catch (NumberFormatException e) {
				plugin.getLogger().warning(
						plugin.pdfFile.getName()
								+ " Not a valid MaterialID!");
				plugin.getLogger()
						.warning(
								plugin.pdfFile.getName()
										+ " Please make sure to check the value of Chopstick: in the config.yml!");
			}
			if (player.getItemInHand().getType() == Material.getMaterial(materialid)) {
				player.sendMessage("eating");
			}
		}
	}
}
