package me.opd.gambitremastered;

import com.github.yannicklamprecht.worldborder.api.WorldBorderApi;
import me.opd.gambitremastered.commands.*;
import me.opd.gambitremastered.game.GameSession;
import me.opd.gambitremastered.game.mechanics.*;
import me.opd.gambitremastered.prizes.PowerupDropListener;
import me.opd.gambitremastered.prizes.PrizeCrateInteractListener;
import me.opd.gambitremastered.prizes.PrizeManager;
import me.opd.gambitremastered.prizes.powerups.FreezeMobs;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class GambitRemastered extends JavaPlugin {

    public static GameSession gameSession;
    public static FileConfiguration config;
    public static GambitRemastered instance;
    public static PrizeManager prizeManager;
    public static WorldBorderApi worldBorderApi;

    @Override
    public void onEnable() {

        instance = this;
        GambitRemastered.config = getConfig();

        Bukkit.getServer().getPluginCommand("gteam").setExecutor(new GTeamCommand());
        Bukkit.getServer().getPluginCommand("start").setExecutor(new StartCommand());
        Bukkit.getServer().getPluginCommand("savelocation").setExecutor(new SaveLocationCommand());
        Bukkit.getServer().getPluginCommand("abort").setExecutor(new AbortCommand());
        Bukkit.getServer().getPluginCommand("token").setExecutor(new TokenCommand());


        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ItemDropProtectionListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OrbPickUpListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerRespawnListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new MobDropDeathListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new OrbBankListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PrizeCrateInteractListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RemoveFireworkDamageListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new PowerupDropListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new FreezeMobs(null), this);


        gameSession = new GameSession();

        gameSession.getArenaManager().syncConfigLocation();

        prizeManager = new PrizeManager();


        RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = getServer().getServicesManager().getRegistration(WorldBorderApi.class);

        if (worldBorderApiRegisteredServiceProvider == null) {
            getLogger().info("API not found");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        worldBorderApi = worldBorderApiRegisteredServiceProvider.getProvider();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
