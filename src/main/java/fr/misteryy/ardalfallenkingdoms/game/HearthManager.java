package fr.misteryy.ardalfallenkingdoms.game;

import org.bukkit.entity.EnderCrystal;

import java.util.HashMap;

public class HearthManager {

    public int heart;
    public String name;
    public EnderCrystal ec;

    public static HashMap<String, HearthManager> enderCrystals = new HashMap<>();

    public HearthManager(EnderCrystal ec, String name) {
        this.name = name;
        this.heart = 1000;
        this.ec = ec;
        enderCrystals.put(name, this);
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public void removeHeart(int i) {
        this.heart-=i;
    }

    public EnderCrystal getEc() {
        return ec;
    }
}
