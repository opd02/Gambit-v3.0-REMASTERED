package me.opd.gambitremastered.util;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemUtil {

    public static ItemStack getItem(Material material, int amount, ArrayList<String> lore, boolean unbreakable, Enchantment... enchantment) {

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setUnbreakable(unbreakable);

        if (lore != null)
            meta.setLore(lore);

        for (Enchantment e : enchantment) {
            meta.addEnchant(e, 1, true);
        }

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getItem(String name, Material material, int amount, ArrayList<String> lore, boolean unbreakable, Enchantment... enchantment) {

        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setUnbreakable(unbreakable);
        meta.setItemName(ChatUtil.format(name));

        if (lore != null) {
            for (String s : lore) {
                lore.set(lore.indexOf(s), ChatUtil.format(s));
            }
            meta.setLore(lore);
        }

        for (Enchantment e : enchantment) {
            meta.addEnchant(e, 1, true);
        }

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack getColoredLeatherItem(TeamType teamType, Material material) {
        ItemStack item = ItemUtil.getItem(material, 1, null,
                true, switch (material) {
                    case LEATHER_HELMET -> Enchantment.PROTECTION;
                    case LEATHER_CHESTPLATE, LEATHER_LEGGINGS -> Enchantment.PROJECTILE_PROTECTION;
                    case LEATHER_BOOTS -> Enchantment.FEATHER_FALLING;
                    default -> null;
                });

        LeatherArmorMeta hatLM = (LeatherArmorMeta) item.getItemMeta();
        assert hatLM != null;
        hatLM.setColor(TeamType.RED.equals(teamType) ? Color.RED : Color.BLUE);
        item.setItemMeta(hatLM);

        return item;
    }

    public static ItemStack NBTStamp(ItemStack item, String key, String value) {
        ItemMeta itemMeta = item.getItemMeta();
        NamespacedKey nsk = new NamespacedKey(GambitRemastered.instance, key);
        assert itemMeta != null;
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        data.set(nsk, PersistentDataType.STRING, value);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static ItemStack getToken(int amount) {
        ItemStack token = ItemUtil.getItem("&l&6Spin Token", Material.SUNFLOWER, amount,
                new ArrayList<>(List.of(ChatColor.GRAY + "Right Click on the ender chest", ChatColor.GRAY + "to get a prize!")), false);

        ItemUtil.NBTStamp(token, "Prize", "Token");
        return token;
    }

    public static ItemStack getOrb() {
        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Orb");
        ArrayList<String> lore = new ArrayList<String>();
        lore.add("Bank these orbs in");
        lore.add("your command tower!");
        itemmeta.setLore(lore);
        item.setItemMeta(itemmeta);
        return item;
    }

}
