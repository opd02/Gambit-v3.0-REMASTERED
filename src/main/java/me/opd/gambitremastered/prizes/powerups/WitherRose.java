package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.managers.PlayerManager;
import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitherRose extends Prize {
    public WitherRose(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);

        PlayerManager.playerSoundForPlayers(Sound.ENTITY_WITHER_HURT, 0.7f);

        for (Player p : GambitRemastered.gameSession.getPlayerManager().getOppositeTeamPlayers(player)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 80, 2, false));
        }
    }
}
