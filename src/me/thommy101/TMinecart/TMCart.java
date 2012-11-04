package me.thommy101.TMinecart;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.inventory.ItemStack;

public class TMCart
{
	/**
	 * Modifies a storage minecart.
	 * Fills/empties a cart with given "amount" of "blockid".
	 * 
	 * @param entity	The entity that is the storage minecart.
	 * @param itemstack	The itemstack that need to be put in/get out of the storage minecart.
	 * @param fill		True if fill, False if empty.
	 * @return			Amount that didn't fit in the cart.
	 */
	public int modify(Entity entity, ItemStack itemstack, boolean fill)
	{
		StorageMinecart storageMinecart=(StorageMinecart) entity;
		if(fill)
		{
			//ItemStack itemstack = new ItemStack(blockid, amount);
			HashMap<Integer, ItemStack> hmLeftover = storageMinecart.getInventory().addItem(itemstack);
			int leftover = 0;
			if(!(hmLeftover.isEmpty()) || hmLeftover==null)
			{
				leftover = hmLeftover.get(0).getAmount();
			}
			return leftover;
		}else{
			//empty
			storageMinecart.getInventory().removeItem(itemstack);
		}
		return 0;
	}
}
