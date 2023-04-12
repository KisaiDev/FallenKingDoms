package fr.misteryy.ardalfallenkingdoms.listeners.player;

import fr.misteryy.ardalfallenkingdoms.GameStatus;
import fr.misteryy.ardalfallenkingdoms.Main;
import fr.misteryy.ardalfallenkingdoms.player.GamePlayer;
import fr.misteryy.ardalfallenkingdoms.runnables.LobbyRunnable;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		
		if (!GameStatus.isStatus(GameStatus.ATTENTE)) {
			player.setGameMode(GameMode.SPECTATOR);
			player.sendMessage(ChatColor.GOLD + "La partie a déjà commencé vous êtes désormais spectateur.");
			
			new GamePlayer(player.getName());
			return;
		}
		
		event.setJoinMessage(Main.getInstance().getPrefix() + "§e" + player.getName() + " §7a rejoint la partie §a(" + Bukkit.getOnlinePlayers().size() + "§a/" + Bukkit.getMaxPlayers() + ")");
		
		new GamePlayer(player.getName());
		GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
		gp.scoreboard.loadScoreboard();
		
		if ((Bukkit.getOnlinePlayers().size() >= 1) && (!(Main.getInstance().lobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(Main.getInstance(), 0L, 20L);
			Main.getInstance().lobbyRunnable.start = true;
		}
		
		// item du menu des teams.
		player.getInventory().setItem(4, new ItemStack(Material.NETHER_STAR, 1));
	}
}