package me.thommy101.TMinecart;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TMContainer
{
	/**
	 * Modifies a container.
	 * Fills/empties a container with given "amount" of "blockid".
	 * 
	 * @param location	Location of the chest.
	 * @param itemstack	The itemstack that need to be put in/get out of the chest.
	 * @param fill		True if fill, False if empty.
	 * @return			Amount that didn't fit in the cart.
	 */
	public int modify(Location location, ItemStack itemstack, boolean fill, int index)
	{
		boolean Bfurnace = false;
		Inventory inventory = null;
//		if(location.getBlock().getTypeId() == 23)
//			inventory = ((Dispenser) container.getState()).getInventory();
		if(location.getBlock().getTypeId() == 54)
			inventory = ((Chest) location.getBlock().getState()).getInventory();
		if(location.getBlock().getTypeId() == 61)
		{
			//if index is larger than 2, it should not be put in the oven
			//return the whole itemstack.
			if(index > 2) return itemstack.getAmount();
			Bfurnace = true;
			inventory = ((Furnace) location.getBlock().getState()).getInventory();
		}
			
		if(fill)
		{
			int leftover = 0;
			if(Bfurnace)
			{
				if(inventory.getItem(index)!=null)
				{
					int amount = itemstack.getAmount() + inventory.getItem(index).getAmount();
					if (inventory.getItem(index).getData().equals(itemstack.getData()))
					{
						if(amount > 64)
						{
							inventory.getItem(index).setAmount(64);
							leftover = amount - 64;
						}else{
							inventory.getItem(index).setAmount(amount);
						}
					}
				}else{//no item in furnace
					inventory.setItem(index, itemstack);
				}
			}else{
				HashMap<Integer, ItemStack> hmLeftover = inventory.addItem(itemstack);
				if(!(hmLeftover.isEmpty()) || hmLeftover==null)
				{
					leftover = hmLeftover.get(0).getAmount();
				}
			}
			return leftover;
		}else{
			//empty
			
			if(Bfurnace)
			{
				ItemStack temp = inventory.getItem(2).clone();
				temp.setAmount(inventory.getItem(2).getAmount()-itemstack.getAmount());
				inventory.setItem(2, temp);
			}else{
				inventory.removeItem(itemstack);
			}

		}
		return 0;
	}
}
