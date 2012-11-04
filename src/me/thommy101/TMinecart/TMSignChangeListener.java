package me.thommy101.TMinecart;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class TMSignChangeListener implements Listener
{
	private static TMinecart plugin;
	
	public TMSignChangeListener(TMinecart instance)
	{
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignChangeEvent(SignChangeEvent event)
	{
		Player player = event.getPlayer();
		if(event.getLine(1).equalsIgnoreCase("[Collect]"))
		{
			if(plugin.perms!=null&&!plugin.perms.playerHas(player, "cnd.collect") ||
					plugin.perms==null&&!player.hasPermission("cnd.collect"))
			{
				event.getBlock().breakNaturally();
				player.sendMessage(ChatColor.RED+"You are not allowed to place an collector");
			}else{
				player.sendMessage(ChatColor.GREEN+"You have placed an collector");
			}
					
		}	
		if(event.getLine(1).equalsIgnoreCase("[Deposit]"))
		{
			if(plugin.perms!=null&&!plugin.perms.playerHas(player, "cnd.deposit") ||
					plugin.perms==null&&!player.hasPermission("cnd.deposit"))
			{
				event.getBlock().breakNaturally();
				player.sendMessage(ChatColor.RED+"You are not allowed to place an depositor");
			}else{
				player.sendMessage(ChatColor.GREEN+"You have placed an depositor");
			}
		}
	}
}
