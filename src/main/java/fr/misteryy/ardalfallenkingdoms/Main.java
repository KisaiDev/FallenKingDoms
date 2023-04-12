package fr.misteryy.ardalfallenkingdoms;

import fr.misteryy.ardalfallenkingdoms.listeners.ListenersManager;
import fr.misteryy.ardalfallenkingdoms.runnables.LobbyRunnable;
import fr.misteryy.ardalfallenkingdoms.utils.RegionManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Villager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class Main extends JavaPlugin {
    private static Main instance;
    public LobbyRunnable lobbyRunnable;
    public List<UUID> red_team = new ArrayList<>();
    public List<UUID> blue_team = new ArrayList<>();
    public boolean assaut, pvp, deathmatch = false;
    public Random random = new Random();
    public RegionManager redBase, blueBase;
    public Map<UUID, fr.misteryy.ardalfallenkingdoms.Villager> villagers = new HashMap<>();

    @Override
    public void onLoad(){
        instance = this;
    }
    @Override
    public void onEnable() {
        new ListenersManager(this).registerListeners();
        GameStatus.setStatus(GameStatus.ATTENTE);
        lobbyRunnable = new LobbyRunnable();
        random = new Random();

        blueBase = new RegionManager(new Location(Bukkit.getWorld("world"), -64, 73, 195), new Location(Bukkit.getWorld("world"), -64, 73, 195));
        redBase = new RegionManager(new Location(Bukkit.getWorld("world"), -64, 73, 410), new Location(Bukkit.getWorld("world"), -64, 73, 410));
    }

    @Override
    public void onDisable() {
        for(Entity entity : Bukkit.getWorld("world").getEntities()){
            if((entity instanceof EnderCrystal) || (entity instanceof Villager)){
                (entity).remove();
            }
        }
    }

    public static Main getInstance() {
        return instance;
    }
    public String getPrefix(){
        return "§6Fallen Kingdoms §f| ";
    }
}
