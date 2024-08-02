package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.Location;

import java.util.HashMap;

public class ArenaManager {

    public static HashMap<String, Location> locations = new HashMap<>();

    public ArenaManager() {
    }

    public void syncConfigLocation() {
        if (GambitRemastered.config.getConfigurationSection("Locations") == null) {
            return;
        }
        for (var loc : GambitRemastered.config.getConfigurationSection("Locations").getKeys(true)) {
            String name = loc.toString().replace("Locations.", "");
            locations.put(name, (Location) GambitRemastered.config.get("Locations." + name));
        }
    }
    //TODO add end portal opening
}
