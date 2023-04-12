package fr.misteryy.ardalfallenkingdoms.listeners.entity;

import fr.misteryy.ardalfallenkingdoms.Main;
import fr.misteryy.ardalfallenkingdoms.game.HearthManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onPlayerJoin(EntityDamageByEntityEvent event) {

        if (!(event.getDamager() instanceof Player)) return;

        if (event.getEntity() instanceof Player) {

            Player victim = (Player) event.getEntity();
            Player damager = (Player) event.getDamager();

            if (Main.getInstance().blue_team.contains(damager.getUniqueId()) && Main.getInstance().blue_team.contains(victim.getUniqueId())) {
                event.setCancelled(true);
            }

            if (Main.getInstance().red_team.contains(damager.getUniqueId()) && Main.getInstance().red_team.contains(victim.getUniqueId())) {
                event.setCancelled(true);
            }
        }

        if ((event.getEntity() instanceof EnderCrystal) && (event.getDamager() instanceof Player)) {

            EnderCrystal ec = (EnderCrystal) event.getEntity();
            Player damager = (Player) event.getDamager();

            // rouge
            if (HearthManager.enderCrystals.get("rouge") != null) {

                EnderCrystal ecRouge = HearthManager.enderCrystals.get("rouge").getEc();

                if (ec.equals(ecRouge)) {

                    // vélocité
                    if (damager.getLocation().distance(ecRouge.getLocation()) <= 3.0f) {
                        if (Main.getInstance().blue_team.contains(damager.getUniqueId())) {
                            damager.setVelocity(new Vector(0, -1, -1.5));
                            event.setCancelled(true);
                        }
                    }

                    // interdiction rouge
                    if (Main.getInstance().red_team.contains(damager.getUniqueId())) {
                        damager.sendMessage("§cVous ne pouvez pas attaquer votre coeur...");
                        event.setCancelled(true);
                        damager.setFireTicks(20);
                        return;
                    }

                    // normal damage
                    if (Main.getInstance().blue_team.contains(damager.getUniqueId())) {
                        HearthManager.enderCrystals.get("rouge").removeHeart( (int) (event.getDamage() / 2) );
                        damager.playSound(damager.getLocation(), Sound.ENTITY_BLAZE_HURT, 1f, 1f);
                        event.setCancelled(true);
                        if ((Math.random() * 100) < 10) {
                            damager.setFireTicks(20 * 5);
                            damager.playSound(damager.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1f, 1f);
                        }

                    }

                    // mort
                    if (event.getDamage() > HearthManager.enderCrystals.get("rouge").getLife()) {

                        HearthManager.enderCrystals.get("rouge").setHeart(0);
                        event.setCancelled(true);

                        if (ecRouge != null) ecRouge.remove();
                        HearthManager.enderCrystals.remove("rouge");

                        Bukkit.getOnlinePlayers().forEach(players -> {
                            players.sendMessage("§bLe coeur de l'équipe §cROUGE §best maintenant détruit !");
                            players.playSound(players.getLocation(), Sound.ENTITY_WITHER_DEATH, 1f, 1f);
                        });

                        // explose tout
                        List<Location> locs = new ArrayList<>();
                        for (int x = Main.getInstance().redBase.minLoc.getBlockX(); x <= Main.getInstance().redBase.maxLoc.getBlockX(); x++) {
                            for (int y = Main.getInstance().redBase.minLoc.getBlockY(); y <= Main.getInstance().redBase.maxLoc.getBlockY(); y++) {
                                for (int z = Main.getInstance().redBase.minLoc.getBlockZ(); z <= Main.getInstance().redBase.maxLoc.getBlockZ(); z++) {

                                    locs.add(new Location(Bukkit.getWorld("world"), x, y, z));

                                }
                            }
                            for (int i = 0; i <= 5; i++) {
                                Location rLoc = locs.get(Main.getInstance().random.nextInt(locs.size()));
                                Bukkit.getWorld("world").createExplosion(rLoc, 1);
                            }
                        }

                        event.setCancelled(true);

                        return;
                    }
                }

            } else {
                event.setCancelled(true);
            }

            // bleu
            if (HearthManager.enderCrystals.get("bleu") != null) {

                EnderCrystal ecBleu = HearthManager.enderCrystals.get("bleu").getEc();

                if (ec.equals(ecBleu)) {

                    // vélocité
                    if (damager.getLocation().distance(ecBleu.getLocation()) <= 3.0f) {
                        if (Main.getInstance().red_team.contains(damager.getUniqueId())) {
                            damager.setVelocity(new Vector(0, -1, -1.5));
                            event.setCancelled(true);
                        }
                    }

                    // interdiction bleue
                    if (Main.getInstance().blue_team.contains(damager.getUniqueId())) {
                        damager.sendMessage("§cVous ne pouvez pas attaquer votre coeur...");
                        event.setCancelled(true);
                        damager.setFireTicks(20);
                        return;
                    }

                    // normal damage
                    if (Main.getInstance().red_team.contains(damager.getUniqueId())) {
                        HearthManager.enderCrystals.get("rouge").removeHeart( (int) (event.getDamage() / 2) );
                        damager.playSound(damager.getLocation(), Sound.ENTITY_BLAZE_HURT, 1f, 1f);
                        event.setCancelled(true);
                        if ((Math.random() * 100) < 10) {
                            damager.setFireTicks(20 * 5);
                            damager.playSound(damager.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 1f, 1f);
                        }

                    }

                    // mort
                    if (event.getDamage() > HearthManager.enderCrystals.get("bleu").getLife()) {

                        HearthManager.enderCrystals.get("bleu").setHeart(0);
                        event.setCancelled(true);

                        if (ecBleu != null) ecBleu.remove();
                        HearthManager.enderCrystals.remove("bleu");

                        Bukkit.getOnlinePlayers().forEach(players -> {
                            players.sendMessage("§bLe coeur de l'équipe §cROUGE §best maintenant détruit !");
                            players.playSound(players.getLocation(), Sound.ENTITY_WITHER_DEATH, 1f, 1f);
                        });

                        // explose tout
                        List<Location> locs = new ArrayList<>();
                        for (int x = Main.getInstance().blueBase.minLoc.getBlockX(); x <= Main.getInstance().blueBase.maxLoc.getBlockX(); x++) {
                            for (int y = Main.getInstance().blueBase.minLoc.getBlockY(); y <= Main.getInstance().blueBase.maxLoc.getBlockY(); y++) {
                                for (int z = Main.getInstance().blueBase.minLoc.getBlockZ(); z <= Main.getInstance().blueBase.maxLoc.getBlockZ(); z++) {

                                    locs.add(new Location(Bukkit.getWorld("world"), x, y, z));

                                }
                            }
                            for (int i = 0; i <= 5; i++) {
                                Location rLoc = locs.get(Main.getInstance().random.nextInt(locs.size()));
                                Bukkit.getWorld("world").createExplosion(rLoc, 1);
                            }
                        }

                        event.setCancelled(true);

                        return;
                    }
                }

            } else {
                event.setCancelled(true);
            }
        }
    }

}
