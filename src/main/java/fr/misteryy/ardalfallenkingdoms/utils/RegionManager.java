package fr.misteryy.ardalfallenkingdoms.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class RegionManager {

public Location minLoc, maxLoc;
	
	public RegionManager(Location firstPoint, Location secondPoint) {
		minLoc = new Location(firstPoint.getWorld(), min(firstPoint.getX(), secondPoint.getX()), min(firstPoint.getY(), secondPoint.getY()), min(firstPoint.getZ(), secondPoint.getZ()));
		maxLoc = new Location(firstPoint.getWorld(), max(firstPoint.getX(), secondPoint.getX()), max(firstPoint.getY(), secondPoint.getY()), max(firstPoint.getZ(), secondPoint.getZ()));
	}

	public double min(double a, double b) {
		return a < b ? a : b;
	}

	public double max(double a, double b) {
		return a > b ? a : b;
	}
	
	public boolean isInArea(Location loc) {
		return (minLoc.getX() <= loc.getX() && minLoc.getZ() <= loc.getZ() && maxLoc.getX() >= loc.getX() && maxLoc.getZ() >= loc.getZ());
	}
	
	public Location getMiddle() {
		double a, b;
		a = (maxLoc.getX() - minLoc.getX()) / 2D + minLoc.getX();
		b = (maxLoc.getZ() - minLoc.getZ()) / 2D + minLoc.getZ();
		
		return new Location(Bukkit.getWorld("world"), a, minLoc.getY(), b);
	}
	
}