package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ChargedCreeperEgg extends Prize {
    public ChargedCreeperEgg(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);

        Creeper creeper = player.getWorld().spawn(player.getLocation(), Creeper.class);
        creeper.setPowered(true);
        creeper.setInvulnerable(true);
        creeper.setExplosionRadius(2);
        creeper.setMaxFuseTicks(50);
        creeper.ignite();

    }
}
