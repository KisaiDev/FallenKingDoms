package fr.misteryy.ardalfallenkingdoms.listeners.entity;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CreatureSpawnListener implements Listener {
    @EventHandler
    public void onSpawn(CreatureSpawnEvent e){
        if(e.getEntityType() == EntityType.CREEPER){
            if((Math.random() * 100) <= 50){
                Creeper creeper = (Creeper) Bukkit.getWorld("world").spawnEntity(e.getEntity().getLocation(),EntityType.CREEPER);
                creeper.setPowered(true);
                e.getEntity().remove();
            }
        }
    }
    @EventHandler
    public void onEntityDamage(EntityDeathEvent e){
        if(e.getEntity() instanceof Creeper){
            Creeper creeper = (Creeper) e.getEntity();
            if(creeper.isPowered()){
                creeper.getWorld().dropItem(creeper.getLocation(),new ItemStack(Material.TNT,1));

            }
        }
        if(e.getEntity() instanceof Zombie){
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),new ItemStack(Material.SAND,2));
        }
        if(e.getEntity() instanceof Enderman){
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),new ItemStack(Material.GOLDEN_APPLE,1));
            if((Math.random() * 100 ) <= 30){
                e.getEntity().getWorld().dropItem(e.getEntity().getLocation(),new ItemStack(Material.DIAMOND,2));
            }
        }
    }
}
