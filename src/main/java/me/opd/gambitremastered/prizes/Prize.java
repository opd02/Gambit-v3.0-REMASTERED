package me.opd.gambitremastered.prizes;

import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Prize {
    private final ItemStack item;

    public Prize(ItemStack item) {
        this.item = item;
    }

    public abstract void onDrop(Player player);

    protected void announceDrop(Player player) {
        Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format(player.getDisplayName() +
                " has activated " + ((item.getItemMeta().getItemName().toLowerCase().charAt(4) == 'a' ||
                item.getItemMeta().getItemName().toLowerCase().charAt(4) == 'i' ||
                item.getItemMeta().getItemName().toLowerCase().charAt(2) == 'e') ? "an " : "a ")
                + item.getItemMeta().getItemName() + " &r&7power-up!"));
    }

    public ItemStack getItem() {
        return item;
    }
}
