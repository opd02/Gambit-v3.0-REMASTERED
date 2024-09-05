package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.managers.PlayerManager;
import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LevitationMembrane extends Prize {
    public LevitationMembrane(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);

        PlayerManager.playerSoundForPlayers(Sound.ENTITY_PHANTOM_BITE, 1);

        for (Player p : GambitRemastered.gameSession.getPlayerManager().getOppositeTeamPlayers(player)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 80, 1, false));
        }

    }
}
