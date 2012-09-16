package me.jacklin213.chopsticks;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.inventory.SpoutItemStack;
import org.getspout.spoutapi.inventory.SpoutShapedRecipe;
import org.getspout.spoutapi.material.MaterialData;

public class Chopsticks extends JavaPlugin {
	PluginDescriptionFile pdfFile;
    public final Logger log = Logger.getLogger("Minecraft");
    public ChopsticksItem ChopsticksItem;
	
    public void onEnable() {
    	this.pdfFile = getDescription();
    	this.getServer().getPluginManager().getPlugin("SpoutPlugin");
        this.log.info(this.pdfFile.getName() + " version " + this.pdfFile.getVersion() + " has been enabled!.");
        SpoutManager.getFileManager().addToPreLoginCache(this, "http://dev.bukkit.org/media/images/44/555/Chopsticks.png");
        ChopsticksItem = new ChopsticksItem(this);
        addChopstickRecipe();
    }
    
    public void onDisable() {
    	this.pdfFile = getDescription();
        this.log.info(this.pdfFile.getName() + " is now disabled!");
    }
    
    public void addChopstickRecipe() {
        SpoutItemStack item = new SpoutItemStack(ChopsticksItem);
        SpoutShapedRecipe recipe = new SpoutShapedRecipe(item);
        recipe.shape("X X", " X ");// top : middle : bottom
        recipe.setIngredient('X', MaterialData.stick);
        SpoutManager.getMaterialManager().registerSpoutRecipe(recipe);
    }

	
    //end of main class
	}