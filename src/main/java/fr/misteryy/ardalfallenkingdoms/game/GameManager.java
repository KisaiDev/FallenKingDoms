package fr.misteryy.ardalfallenkingdoms.game;

import fr.misteryy.ardalfallenkingdoms.Main;
import fr.misteryy.ardalfallenkingdoms.player.GamePlayer;
import fr.misteryy.ardalfallenkingdoms.utils.TitleManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class GameManager {
    private Location redSpawn, blueSpawn;
    public GameManager(){
        this.redSpawn = new Location(Bukkit.getWorld("world"),0,0,0);
        this.blueSpawn = new Location(Bukkit.getWorld("world"),0,0,0);
    }
    public void loadGame(){
        Bukkit.getWorld("world").setPVP(false);
        Bukkit.getWorld("world").setTime(0);

        this.create(new Location(Bukkit.getWorld("world"), -583.5, 85, 395.5), "rouge");
        this.create(new Location(Bukkit.getWorld("world"), -423.5, 94, 382), "bleu");
        for(Player player : Bukkit.getOnlinePlayers()){
            GamePlayer gp = GamePlayer.gamePlayers.get(player.getName());
            gp.scoreboard.loadScoreboardGame();
            if(Main.getInstance().red_team.contains(player.getUniqueId())){
                player.teleport(getRedSpawn());
            }else if(Main.getInstance().blue_team.contains(player.getUniqueId())){
                player.teleport(getBlueSpawn());
            }
            TitleManager.sendTitle(player,"§6Fallen Kingdoms","§eLa partie a commencé",40);
        }
    }
    private void create(Location loc, String name) {
        EnderCrystal enderCrystal = (EnderCrystal) Bukkit.getWorld("world").spawnEntity(loc, EntityType.ENDER_CRYSTAL);
        enderCrystal.setCustomName(name);
        enderCrystal.setCustomNameVisible(false);

        new HearthManager(enderCrystal, name);
    }
    public Location getBlueSpawn() {
        return blueSpawn;
    }

    public Location getRedSpawn() {
        return redSpawn;
    }
}
