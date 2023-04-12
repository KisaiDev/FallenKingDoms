package fr.misteryy.ardalfallenkingdoms.listeners;

import fr.misteryy.ardalfallenkingdoms.listeners.entity.CreatureSpawnListener;
import fr.misteryy.ardalfallenkingdoms.listeners.entity.EntityDamageListener;
import fr.misteryy.ardalfallenkingdoms.listeners.player.*;
import fr.misteryy.ardalfallenkingdoms.menus.TeamsMenu;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenersManager {
    public Plugin plugin;
    public PluginManager pm;
    public ListenersManager(Plugin plugin){
        this.plugin = plugin;
        this.pm = Bukkit.getPluginManager();
    }
    public void registerListeners(){
        pm.registerEvents(new PlayerJoinListener(), this.plugin);
        pm.registerEvents(new PlayerMoveListener(), this.plugin);
        pm.registerEvents(new PlayerBlockListener(), this.plugin);
        pm.registerEvents(new CreatureSpawnListener(), this.plugin);
        pm.registerEvents(new FurnasePropertyListener(), this.plugin);
        pm.registerEvents(new PlayerQuitListener(), this.plugin);
        pm.registerEvents(new EntityDamageListener(), this.plugin);

        pm.registerEvents(new TeamsMenu(), this.plugin);
    }
}
