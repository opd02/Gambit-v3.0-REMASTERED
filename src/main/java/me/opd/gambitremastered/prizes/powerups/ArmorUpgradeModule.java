package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.prizes.Prize;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class ArmorUpgradeModule extends Prize {
    public ArmorUpgradeModule(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        PlayerInventory pi = player.getInventory();
        if (pi.getChestplate().getType() == Material.DIAMOND_CHESTPLATE) {
            player.sendMessage(ChatUtil.prefix + ChatUtil.format("&cYour armor is already maxed out!"));
            player.getWorld().dropItemNaturally(player.getLocation(), GambitRemastered.prizeManager.getPossiblePrizes().get(2).getItem());
            return;
        }
        
        announceDrop(player);
        player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_IRON, 1, 0.75f);

        if (pi.getChestplate().getType() == Material.LEATHER_CHESTPLATE) {
            pi.getHelmet().setType(Material.IRON_HELMET);
            pi.getChestplate().setType(Material.IRON_CHESTPLATE);
            pi.getLeggings().setType(Material.IRON_LEGGINGS);
            pi.getBoots().setType(Material.IRON_BOOTS);
            return;
        }
        pi.getHelmet().setType(Material.DIAMOND_HELMET);
        pi.getChestplate().setType(Material.DIAMOND_CHESTPLATE);
        pi.getLeggings().setType(Material.DIAMOND_LEGGINGS);
        pi.getBoots().setType(Material.DIAMOND_BOOTS);

    }
}
