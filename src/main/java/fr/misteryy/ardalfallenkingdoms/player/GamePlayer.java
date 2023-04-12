package fr.misteryy.ardalfallenkingdoms.player;

import fr.misteryy.ardalfallenkingdoms.scoreboard.ScoreboardManager;
import fr.misteryy.ardalfallenkingdoms.utils.RegionManager;
import jdk.nashorn.internal.ir.Block;
import org.bukkit.Bukkit;

import java.util.*;

public class GamePlayer {
    private String name;
    private List<UUID> team;

    public boolean isInBase;
    public RegionManager region;

    public List<Block> fours = new ArrayList<>();

    public static Map<String, GamePlayer> gamePlayers = new HashMap<>();
    public ScoreboardManager scoreboard;

    public GamePlayer(String name) {
        this.name = name;
        this.team = new ArrayList<UUID>();
        this.isInBase = false;

        scoreboard = new ScoreboardManager(Bukkit.getPlayer(name));
        gamePlayers.put(name, this);
    }

    public String getName() {
        return name;
    }

    public void setTeam(List<UUID> team) {
        this.team = team;
    }

    public List<UUID> getTeam() {
        return team;
    }
}
