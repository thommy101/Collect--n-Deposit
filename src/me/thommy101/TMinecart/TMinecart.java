package me.thommy101.TMinecart;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TMinecart extends JavaPlugin
{
	public String logprefix;
	public Logger log = Logger.getLogger("Minecraft");
	public PluginManager pm = Bukkit.getServer().getPluginManager();
	public TMRedstoneListener redstoneListener = new TMRedstoneListener(this);
	
	public void onDisable()
	{
		//nothing
	}

	public void onEnable()
	{
		//Set logprefix
		logprefix = "["+ getName() + "] ";
		pm.registerEvents(redstoneListener, this);
	}
}
