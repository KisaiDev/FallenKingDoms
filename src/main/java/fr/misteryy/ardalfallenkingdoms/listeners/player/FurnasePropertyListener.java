package fr.misteryy.ardalfallenkingdoms.listeners.player;

import fr.misteryy.ardalfallenkingdoms.menus.TeamsMenu;
import fr.misteryy.ardalfallenkingdoms.player.GamePlayer;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class FurnasePropertyListener implements Listener {

	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent event) {
		
		Player player = event.getPlayer();
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		
		switch (event.getBlock().getType()) {
		case FURNACE:
			
			player.sendMessage("§c[Info] Ce four vous appartient, aucun autre joueur peut intéragir avec.");
			gp.fours.add((Block) event.getBlock());
			
			break;

		default:
			break;
		}
	}
	
	@EventHandler
	public void onBreakBlock(BlockBreakEvent event) {
		
		Player player = event.getPlayer();
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		
		switch (event.getBlock().getType()) {
		case FURNACE:
			
			if (gp.fours.contains(event.getBlock())) {
				gp.fours.remove(event.getBlock());
			}
			
			break;

		default:
			break;
		}
	}
	
	@EventHandler
	public void onFurnaseInteract(PlayerInteractEvent event) {
		
		if (event.getAction() != null && event.getItem() == null) return;
		
		Player player = event.getPlayer();
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			if (event.getItem().getType() == Material.NETHER_STAR) {
				new TeamsMenu().open(player);
			}
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			
			switch (event.getClickedBlock().getType()) {
			case FURNACE:
				
				// si un joueur intéragit avec un four qui n'est pas le sien
				if (!gp.fours.contains(event.getClickedBlock())) {
					event.setCancelled(true);
					player.sendMessage("§cEh mon coco ! Ceci n'est pas ton four :c");
				}
				
				break;

			default:
				break;
			}
		}
	}
}