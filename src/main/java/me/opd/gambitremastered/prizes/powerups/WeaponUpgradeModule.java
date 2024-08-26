package me.opd.gambitremastered.prizes.powerups;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.prizes.Prize;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WeaponUpgradeModule extends Prize {
    public WeaponUpgradeModule(ItemStack item) {
        super(item);
    }

    @Override
    public void onDrop(Player player) {
        if (player.getInventory().contains(Material.DIAMOND_AXE)) {
            player.sendMessage(ChatUtil.prefix + ChatUtil.format("&cYour weapons are already maxed out!"));
            Item item = player.getWorld().dropItemNaturally(player.getLocation(), GambitRemastered.prizeManager.getPossiblePrizes().get(3).getItem());
            item.setPickupDelay(20);
            return;
        }
        announceDrop(player);
        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);

        for (ItemStack i : player.getInventory().getContents()) {
            if (i == null) {
                continue;
            }
            if (i.getType() == Material.IRON_AXE) {
                i.setType(Material.DIAMOND_AXE);
            }
            if (i.getType() == Material.BOW) {
                i.addEnchantment(Enchantment.POWER, 2);
            }
            if (i.getType() == Material.IRON_SWORD) {
                i.setType(Material.DIAMOND_SWORD);
            }
        }
    }
}
