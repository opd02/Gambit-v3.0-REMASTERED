package me.opd.gambitremastered.commands.gteamGUI;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    private final int slot;

    public Button(int slot) {
        this.slot = slot;
    }

    public abstract ItemStack getItem();

    public abstract void onClick(Player player);

    public int getSlot() {
        return slot;
    }
}
