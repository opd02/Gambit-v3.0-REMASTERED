package me.opd.gambitremastered.game.mechanics;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OrbBankListener implements Listener {
    @EventHandler
    public void onPlayerClickBotton(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = e.getClickedBlock();
        if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
            return;
        }
        assert b != null;
        if (!(b.getType() == Material.STONE_BUTTON)) {
            return;
        }
        int levels = p.getLevel();
        TeamType team = GambitRemastered.gameSession.getPlayerManager().getPlayerTeam(p);
        if (team == TeamType.SPECTATE) {
            p.sendMessage(ChatUtil.prefix + ChatUtil.format("&cYou cannot bank orbs while spectating."));
            return;
        }
        if (levels == 0) {
            p.sendMessage(ChatUtil.prefix + ChatColor.RED + "You have nothing to bank!");
            p.playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 10, (float) 1);
        } else {
            GambitRemastered.gameSession.getScoreManager().tryToAddPoints(team, levels, p);
        }

    }
}
