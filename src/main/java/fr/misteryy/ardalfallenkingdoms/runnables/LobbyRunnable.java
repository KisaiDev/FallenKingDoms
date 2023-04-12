package fr.misteryy.ardalfallenkingdoms.runnables;

import fr.misteryy.ardalfallenkingdoms.GameStatus;
import fr.misteryy.ardalfallenkingdoms.Main;
import fr.misteryy.ardalfallenkingdoms.game.GameManager;
import fr.misteryy.ardalfallenkingdoms.scoreboard.ScoreboardManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LobbyRunnable extends BukkitRunnable {
	
	public int timer = 20;
	public boolean start = false;

	@Override
	public void run() {
		
		if (!(GameStatus.isStatus(GameStatus.ATTENTE))) {
			timer = 121;
			start = false;
			this.cancel();
			return;
		}
		
		if (Bukkit.getOnlinePlayers().size() < 1) {
			Bukkit.broadcastMessage("§cIl n'y a pas assez de joueurs pour lancer la partie.");
			timer = 121;
			start = false;
			setLevel();
			this.cancel();
			return;
		}
		timer--;
		setLevel();
		
		if (timer == 0) {
			this.cancel();
			GameStatus.setStatus(GameStatus.GAME);
			new GameManager().loadGame();
			new GameRunnable().runTaskTimer(Main.getInstance(), 0L, 20L);
		}
		
		if ((timer == 120) || (timer == 90) || (timer == 60) || (timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5 && timer != 0)) {
			Bukkit.broadcastMessage("§6Démarrage de la partie dans §e" + timer + " " + getSecond() + "§6.");
			for (Player players : Bukkit.getOnlinePlayers()) {
				players.playSound(players.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10f, 1f);
			}
		}
	}

	private String getSecond() { return timer == 1 ? "seconde" : "secondes"; }
	
	private void setLevel() {
		for (Player players : Bukkit.getOnlinePlayers()) {
			players.setLevel(timer);
			if (ScoreboardManager.scoreboardGame.containsKey(players)) {
				ScoreboardManager.scoreboardGame.get(players).setLine(9, ChatColor.WHITE + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("mm:ss").format(new Date(timer * 1000)));
				ScoreboardManager.scoreboardGame.get(players).setLine(8, ChatColor.WHITE + "Joueurs: " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size() + "§8/§e" + Bukkit.getMaxPlayers());
			}
		}
	}
}