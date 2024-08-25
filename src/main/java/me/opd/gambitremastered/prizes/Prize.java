package me.opd.gambitremastered.prizes;

import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Prize {
    //TODO Give each prize their drop functionality
    private final ItemStack item;

    public Prize(ItemStack item) {
        this.item = item;
    }

    public abstract void onDrop(Player player);

    protected void announceDrop(Player player) {
        Bukkit.getServer().broadcastMessage(ChatUtil.prefix + ChatUtil.format(player.getDisplayName() +
                " has activated " + (item.getItemMeta().getItemName().toLowerCase().charAt(0) == 'a' ? "a" : "an")
                + item.getItemMeta().getDisplayName() + " &r&7power-up!"));
    }

    protected ItemStack getItem() {
        return item;
    }
}
