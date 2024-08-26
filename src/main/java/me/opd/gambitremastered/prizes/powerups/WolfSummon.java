package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.prizes.Prize;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;

public class WolfSummon extends Prize {
    public WolfSummon(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        announceDrop(player);
        player.playSound(player.getLocation(), Sound.ENTITY_WOLF_AMBIENT, 0.5f, 0.75f);

        Wolf wolf = (Wolf) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
        wolf.setAdult();
        wolf.setTamed(true);
        wolf.setOwner(player);
        wolf.setBreed(false);
        wolf.setCustomName(ChatColor.YELLOW + player.getName() + "'s Wolf");
        wolf.setCustomNameVisible(true);
        wolf.setCollarColor(DyeColor.GREEN);
        wolf.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(55);
        wolf.setHealth(55);
    }
}
