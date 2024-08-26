package me.opd.gambitremastered.prizes;

import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class PowerupDropListener implements Listener {
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItemDrop().getItemStack();
        NamespacedKey nsk = new NamespacedKey(GambitRemastered.instance, "Prize");

        if (item.getItemMeta().getPersistentDataContainer().has(nsk, PersistentDataType.STRING)) {

            ItemStack dropped = event.getItemDrop().getItemStack();

            Prize droppedPrize = null;
            switch (item.getItemMeta().getPersistentDataContainer().get(nsk, PersistentDataType.STRING)) {
                case "Token":
                    return;
                case "ArmorUpgradeModule":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(2);
                    break;
                case "FreezeMobs":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(5);
                    break;
                case "InkBomb":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(0);
                    break;
                case "SpeedBoost":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(1);
                    break;
                case "TeamSlowDown":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(8);
                    break;
                case "WeaponUpgradeModule":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(3);
                    break;
                case "WolfSummon":
                    droppedPrize = GambitRemastered.prizeManager.getPossiblePrizes().get(4);
                    break;
                default:
            }
            assert droppedPrize != null;
            droppedPrize.onDrop(player);

            if (dropped.getAmount() > 1) {
                player.getInventory().setItem(player.getInventory().getHeldItemSlot(), dropped);
                player.updateInventory();
                player.getInventory().getItem(player.getInventory().getHeldItemSlot()).setAmount(dropped.getAmount() - 1);
                player.updateInventory();
                dropped.setAmount(1);
            }
            event.getItemDrop().remove();

        }
    }
}
