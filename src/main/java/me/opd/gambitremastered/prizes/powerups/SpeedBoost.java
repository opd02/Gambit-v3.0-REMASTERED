package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpeedBoost extends Prize {

    public SpeedBoost(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);

    }
}
