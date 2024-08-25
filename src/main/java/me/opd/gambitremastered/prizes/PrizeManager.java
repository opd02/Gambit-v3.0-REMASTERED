package me.opd.gambitremastered.prizes;

import me.opd.gambitremastered.prizes.powerups.*;
import me.opd.gambitremastered.util.ChatUtil;
import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PrizeManager {

    private final ArrayList<Prize> possiblePrizes = new ArrayList<>();

    public PrizeManager() {
        possiblePrizes.add(new InkBomb(ItemUtil.getItem("&7&lInk Bomb", Material.INK_SAC, 1,
                new ArrayList<>(List.of("&7Drop this item to give your opposing", "&7team a temporary blindness!")), false)));

        possiblePrizes.add(new SpeedBoost(ItemUtil.getItem("&e&lTeam Speed Boost", Material.SUGAR, 1,
                new ArrayList<>(List.of("&7Drop this item to give your entire", "&7team a speed boost!")), false)));

        possiblePrizes.add(new ArmorUpgradeModule(ItemUtil.getItem("&b&lArmor Upgrade Module", Material.DIAMOND, 1,
                new ArrayList<>(List.of("&7Drop this item to upgrade your", "&7armor material by 1 type")), false)));

        possiblePrizes.add(new WeaponUpgradeModule(ItemUtil.getItem("&5&lWeapon Upgrade Module", Material.ANVIL, 1,
                new ArrayList<>(List.of("&7Drop this item to upgrade your", "&7weapon material by 1 type")), false)));

        possiblePrizes.add(new WolfSummon(ItemUtil.getItem("&9&lSummon Wolf", Material.BONE, 2,
                new ArrayList<>(List.of("&7Drop this item to summon a", "&7wolf companion to fight for you")), false)));

        possiblePrizes.add(new FreezeMobs(ItemUtil.getItem("&1&lFreeze Near Mobs", Material.ICE, 2,
                new ArrayList<>(List.of("&7Drop this item to freeze", "&7all nearby mobs in place!")), false)));

        possiblePrizes.add(new HotHands(ItemUtil.getItem("&6&lHot Hands", Material.MAGMA_CREAM, 1,
                new ArrayList<>(List.of("", "&7Use this item as a weapon to", "&7set your enemy ablaze!")), false, Enchantment.FIRE_ASPECT)));

        possiblePrizes.add(new NotchApple(ItemUtil.getItem("&dEnchanted Golden Apple", Material.ENCHANTED_GOLDEN_APPLE, 1,
                null, false)));

        possiblePrizes.add(new TeamSlowDown(ItemUtil.getItem("&2&lTeam Slow Down", Material.TURTLE_SCUTE, 2,
                new ArrayList<>(List.of("&7Drop this item to give your opponents", "&7a massive reduction in speed!")), false)));
    }

    public void givePlayerRandomPrize(Player player) {
        Prize prize = randomPrize();
        ItemStack item = prize.getItem();

        player.getInventory().addItem(prize.getItem());
        player.sendMessage(ChatUtil.prefix + ChatUtil.format("You have received " + ((item.getItemMeta().getItemName().toLowerCase().charAt(4) == 'a' ||
                item.getItemMeta().getItemName().toLowerCase().charAt(4) == 'i' ||
                item.getItemMeta().getItemName().toLowerCase().charAt(2) == 'e') ? "an " : "a ") + item.getItemMeta().getItemName() + "&7!"));

    }

    private Prize randomPrize() {
        Random random = new Random();
        int index = random.nextInt(possiblePrizes.size());
        return possiblePrizes.get(index);
    }
}
