package me.thommy101.TMinecart;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class TMRedstoneListener implements Listener
{
	private static TMinecart plugin;
	
	public TMRedstoneListener(TMinecart instance)
	{
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onRedstoneChange(BlockRedstoneEvent event)
	{
		Block dRail = event.getBlock();
		//If block isn't a detectorrail || new current isn't 1(ON) -> return
		if(dRail.getTypeId()!=28 || event.getNewCurrent()!=1) return;
		Location loc = new Location(dRail.getWorld(), dRail.getX(), dRail.getY()-2, dRail.getZ());
		Sign sign = null;
		if(loc.getBlock().getTypeId()==63||loc.getBlock().getTypeId()==68)
			sign = (Sign)loc.getBlock().getState();
		if(sign == null) return;

		String line1 = sign.getLine(1);
		if(line1.trim().equalsIgnoreCase("[collect]"))
		{
			plugin.generic.Collect(dRail, sign);
		}
		else if (line1.trim().equalsIgnoreCase("[deposit]"))
		{
			plugin.generic.Deposit(dRail, sign);
		}
	}
}