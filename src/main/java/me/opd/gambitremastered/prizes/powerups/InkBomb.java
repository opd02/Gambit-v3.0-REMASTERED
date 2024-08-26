package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.managers.PlayerManager;
import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class InkBomb extends Prize {
    public InkBomb(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);
        PlayerManager.playerSoundForPlayers(Sound.ENTITY_SQUID_SQUIRT, 1);

        for (Player p : GambitRemastered.gameSession.getPlayerManager().getOppositeTeamPlayers(player)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 160, 2, false));
        }
    }
}
