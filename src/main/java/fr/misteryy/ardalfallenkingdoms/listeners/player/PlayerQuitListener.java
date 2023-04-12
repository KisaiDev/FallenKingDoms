package fr.misteryy.ardalfallenkingdoms.listeners.player;

import fr.misteryy.ardalfallenkingdoms.GameStatus;
import fr.misteryy.ardalfallenkingdoms.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerQuitListener implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		
		event.setQuitMessage(null);
		
		if (!GameStatus.isStatus(GameStatus.ATTENTE)) createPNJ(player);
	}
	
	@EventHandler
	public void onVillagerAttack(EntityDeathEvent event) {
		
		if (!(event.getEntity() instanceof Villager)) return;
		
		Villager villager = (Villager) event.getEntity();
		Player player = (Player) event.getEntity().getKiller();
		
		fr.misteryy.ardalfallenkingdoms.Villager v = Main.getInstance().villagers.get(player.getUniqueId());
		
		if (villager.getCustomName().equalsIgnoreCase(v.getVillagerID())) {
			
			for (ItemStack i : v.getInventory()) {
				Bukkit.getWorld("world").dropItemNaturally(player.getLocation(), i);
			}
			Main.getInstance().villagers.remove(player.getUniqueId());
		}
	}
	
	private void createPNJ(Player player) {
		
		Villager villager = (Villager) Bukkit.getWorld("world").spawnEntity(player.getLocation(), EntityType.VILLAGER);
		// name floating text
		villager.setCustomName(player.getName());
		villager.setCustomNameVisible(true);
		
		// inventory
		Main.getInstance().villagers.put(player.getUniqueId(), new fr.misteryy.ardalfallenkingdoms.Villager(player.getName(), player));
	}
}