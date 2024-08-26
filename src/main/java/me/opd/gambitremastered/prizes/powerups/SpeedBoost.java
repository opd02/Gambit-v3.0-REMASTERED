package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.managers.PlayerManager;
import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedBoost extends Prize {

    public SpeedBoost(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);
        PlayerManager.playerSoundForPlayers(Sound.ENTITY_ENDER_DRAGON_FLAP, 2f);

        for (Player p : GambitRemastered.gameSession.getPlayerManager().getTeamPlayers(player)) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 1, false));
        }
    }
}
