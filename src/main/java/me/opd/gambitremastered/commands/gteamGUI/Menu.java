package me.opd.gambitremastered.commands.gteamGUI;

import me.opd.gambitremastered.GambitRemastered;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private final List<Button> buttons = new ArrayList<>();

    private int size = 9;
    private String title = "Menu Title";

    public final List<Button> getButtons() {
        return buttons;
    }

    protected final void addButton(Button button) {
        this.buttons.add(button);
    }

    protected final void setSize(int size) {
        this.size = size;
    }

    protected final void setTitle(String title) {
        this.title = title;
    }

    public final void displayTo(Player player) {

        Inventory inv = Bukkit.createInventory(player, this.size, this.title);

        for (Button button : this.buttons) {
            inv.setItem(button.getSlot(), button.getItem());

            player.openInventory(inv);

            player.setMetadata("Gambit", new FixedMetadataValue(GambitRemastered.instance, this));
        }
    }
}