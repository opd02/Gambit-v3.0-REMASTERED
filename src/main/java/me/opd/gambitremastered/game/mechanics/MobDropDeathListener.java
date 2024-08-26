package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class MobDropDeathListener implements Listener {

    @EventHandler
    public void onMobDie(EntityDeathEvent e) {
        Entity en = e.getEntity();
        Location l = en.getLocation();

        if (e.getEntityType().equals(EntityType.ZOMBIE) || e.getEntityType().equals(EntityType.PILLAGER)) {
            en.getWorld().dropItemNaturally(l, ItemUtil.getOrb());

        } else if (e.getEntityType().equals(EntityType.RAVAGER) || e.getEntityType().equals(EntityType.VINDICATOR) || e.getEntityType().equals(EntityType.WITHER)) {
            en.getWorld().dropItemNaturally(l, ItemUtil.getToken(3));
            Player killer = e.getEntity().getKiller();
            assert killer != null;
            GambitRemastered.gameSession.getScoreManager().increaseBossLevel(GambitRemastered.gameSession.getPlayerManager().getPlayerTeam(killer));
        }
        if (e.getEntityType().equals(EntityType.WITHER)) {
            Player killer = e.getEntity().getKiller();
            TeamType team = GambitRemastered.gameSession.getPlayerManager().getPlayerTeam(killer);
            GambitRemastered.gameSession.getArenaManager().openEndPortal(team);
            GambitRemastered.gameSession.setAllowRespawning(false);
            Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format("&2&lRespawning has now been disabled."));
        }
    }
}
