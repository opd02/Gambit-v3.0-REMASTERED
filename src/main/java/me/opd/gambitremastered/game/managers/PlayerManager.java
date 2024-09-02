package me.opd.gambitremastered.game.managers;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class PlayerManager {

    private HashMap<UUID, TeamType> players = new HashMap<>();
    private ArrayList<UUID> slimeLaunching = new ArrayList<>();

    public HashMap<UUID, TeamType> getPlayers() {
        return players;
    }

    public void addPlayerToTeam(Player player, TeamType team) {
        UUID uuid = player.getUniqueId();
        players.remove(uuid);
        players.put(uuid, team);
        player.sendMessage(ChatUtil.prefix + String.format("You have been added to the %s%s team.",
                team.equals(TeamType.SPECTATE) ? ChatColor.GRAY : ChatColor.valueOf(team.name().toUpperCase()), (team + "" + ChatColor.GRAY)));
        player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 1.0F);

        String name = "";

        switch (team) {
            case SPECTATE -> name = (ChatUtil.format("&r&7&o" + player.getName() + "&7"));
            case RED -> name = (ChatUtil.format("&r&c" + player.getName() + "&7"));
            case BLUE -> name = (ChatUtil.format("&r&9" + player.getName() + "&7"));
        }

        player.setPlayerListName(name);
        player.setDisplayName(name);
    }

    public void giveStartingGear() {
        for (UUID uuid : players.keySet()) {
            TeamType team = players.get(uuid);

            if (team.equals(TeamType.SPECTATE))
                continue;

            Player player = Bukkit.getPlayer(uuid);
            assert player != null;
            PlayerInventory inv = player.getInventory();

            inv.setItem(0, ItemUtil.getItem(Material.IRON_SWORD, 1, null, true, Enchantment.SHARPNESS));
            inv.setItem(8, ItemUtil.getItem(Material.BOW, 1, null, true, Enchantment.INFINITY));
            inv.setItem(17, ItemUtil.getItem(Material.ARROW, 1, null, false));
            inv.setItem(7, ItemUtil.getItem(Material.COOKED_BEEF, 64, null, false));
            inv.setItem(1, ItemUtil.getItem(Material.IRON_AXE, 1, null, true, Enchantment.SMITE));
            inv.setItem(6, ItemUtil.getItem(Material.GOLDEN_APPLE, 2, null, false));
            inv.setItem(5, ItemUtil.getItem(Material.ENDER_PEARL, 4, null, false));

            inv.setHelmet(ItemUtil.getColoredLeatherItem(players.get(uuid), Material.LEATHER_HELMET));
            inv.setChestplate(ItemUtil.getColoredLeatherItem(players.get(uuid), Material.LEATHER_CHESTPLATE));
            inv.setLeggings(ItemUtil.getColoredLeatherItem(players.get(uuid), Material.LEATHER_LEGGINGS));
            inv.setBoots(ItemUtil.getColoredLeatherItem(players.get(uuid), Material.LEATHER_BOOTS));

            player.setHealth(20);
            player.setFoodLevel(20);
            player.setLevel(0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 1, true, false));

            playerSoundForPlayers(Sound.ITEM_ARMOR_EQUIP_LEATHER, 0.5f);
        }
    }

    public void teleportPlayersToTeamSpawns() {
        for (UUID uuid : players.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            assert player != null;

            switch (TeamType.getTeam(uuid)) {
                case BLUE -> {
                    player.teleport(ArenaManager.locations.get("BlueSpawn"));
                    player.setRespawnLocation(ArenaManager.locations.get("BlueSpawn"), true);
                }
                case RED -> {
                    player.teleport(ArenaManager.locations.get("RedSpawn"));
                    player.setRespawnLocation(ArenaManager.locations.get("RedSpawn"), true);
                }
                case SPECTATE -> {
                    player.teleport(ArenaManager.locations.get("SpectateSpawn"));
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
            GambitRemastered.worldBorderApi.setBorder(player, 10, player.getLocation());
            player.setGameMode(GameMode.ADVENTURE);
        }
        playerSoundForPlayers(Sound.ENTITY_ENDER_DRAGON_FLAP, 0.5f);
    }

    public void clearAllTeams() {
        players.clear();
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.setPlayerListName(player.getName());
            player.setDisplayName(player.getName());
        }
    }

    public static void playerSoundForPlayers(Sound sound, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), sound, 1f, pitch);
        }
    }

    public TeamType getPlayerTeam(Player player) {
        return players.get(player.getUniqueId());
    }

    public ArrayList<Player> getTeamPlayers(Player player) {
        ArrayList<Player> team = new ArrayList<>();
        for (UUID uuid : players.keySet()) {
            if (players.get(uuid) == getPlayerTeam(player)) {
                team.add(Bukkit.getPlayer(uuid));
            }
        }
        return team;
    }

    public ArrayList<Player> getTeamPlayers(TeamType player) {
        ArrayList<Player> team = new ArrayList<>();
        for (UUID uuid : players.keySet()) {
            if (players.get(uuid) == player) {
                team.add(Bukkit.getPlayer(uuid));
            }
        }
        return team;
    }

    public ArrayList<Player> getOppositeTeamPlayers(Player player) {
        ArrayList<Player> team = new ArrayList<>();
        for (UUID uuid : players.keySet()) {
            if (players.get(uuid) != getPlayerTeam(player)) {
                team.add(Bukkit.getPlayer(uuid));
            }
        }
        return team;
    }

    public void teleportPlayersToLobby() {
        for (Player p : Bukkit.getServer().getOnlinePlayers()) {
            p.getInventory().clear();
            p.getEquipment().clear();
            p.setLevel(0);
            p.setHealth(20);
            p.teleport(ArenaManager.locations.get("LobbySpawn"));
            p.getInventory().addItem(ItemUtil.getItem("&e&lTeam Selector &7(Right Click)", Material.COMPASS, 1,
                    new ArrayList<>(List.of("&fRight click this item to select", "&fa team to join")), false));


            GambitRemastered.worldBorderApi.resetWorldBorderToGlobal(p);
        }
    }

    public ArrayList<UUID> getSlimeLaunching() {
        return slimeLaunching;
    }
}
