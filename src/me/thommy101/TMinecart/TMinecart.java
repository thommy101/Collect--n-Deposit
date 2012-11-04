package me.thommy101.TMinecart;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.milkbowl.vault.permission.Permission;

public class TMinecart extends JavaPlugin
{
	public String logprefix;
	public Logger log = Logger.getLogger("Minecraft");
	public boolean LWCEnabled = false;
	public Permission perms = null;
	
	public TMGeneric generic = new TMGeneric(this);
	
	public PluginManager pm = Bukkit.getServer().getPluginManager();
	public TMRedstoneListener redstoneListener = new TMRedstoneListener(this);
	public TMSignChangeListener signPlaceListener = new TMSignChangeListener(this);
	
	public void onDisable()
	{
		//nothing
	}

	public void onEnable()
	{
		//Set logprefix
		logprefix = "["+ getName() + "] ";
		
		//Enable events
		pm.registerEvents(redstoneListener, this);
		pm.registerEvents(signPlaceListener, this);
		
		if(pm.isPluginEnabled("LWC")) LWCEnabled=true;
		setupPermissions();
	}
	
	private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

}
