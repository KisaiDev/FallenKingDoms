package fr.misteryy.ardalfallenkingdoms;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Villager {

	public Player player;
	public String villagerid;

	public Villager(String id, Player player) {
		this.villagerid = id;
		this.player = player;
	}

	public Player getPlayer() {
		return player;
	}

	public String getVillagerID() {
		System.out.println("v " + villagerid);
		return villagerid;
	}

	public List<ItemStack> getInventory() {
		List<ItemStack> item = new ArrayList<>();
		for (ItemStack i : player.getInventory().getContents()) {
			if (i != null && (!i.getType().equals(Material.AIR)))
				item.add(i);
		}
		return item;
	}
}