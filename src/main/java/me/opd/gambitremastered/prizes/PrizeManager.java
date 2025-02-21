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
        possiblePrizes.add(new InkBomb(ItemUtil.NBTStamp(ItemUtil.getItem("&7&lInk Bomb", Material.INK_SAC, 1,
                        new ArrayList<>(List.of("&7Drop this item to give your opposing", "&7team a temporary blindness!")), false), "Prize",
                "InkBomb")));

        possiblePrizes.add(new SpeedBoost(ItemUtil.NBTStamp(ItemUtil.getItem("&e&lTeam Speed Boost", Material.SUGAR, 1,
                        new ArrayList<>(List.of("&7Drop this item to give your entire", "&7team a speed boost!")), false), "Prize",
                "SpeedBoost")));

        possiblePrizes.add(new ArmorUpgradeModule(ItemUtil.NBTStamp(ItemUtil.getItem("&5&lArmor Upgrade Module", Material.DIAMOND, 1,
                        new ArrayList<>(List.of("&7Drop this item to upgrade your", "&7armor material by 1 type")), false), "Prize",
                "ArmorUpgradeModule")));

        possiblePrizes.add(new WeaponUpgradeModule(ItemUtil.NBTStamp(ItemUtil.getItem("&3&lWeapon Upgrade Module", Material.ANVIL, 1,
                        new ArrayList<>(List.of("&7Drop this item to upgrade your", "&7weapon material by 1 type")), false), "Prize",
                "WeaponUpgradeModule")));

        possiblePrizes.add(new WolfSummon(ItemUtil.NBTStamp(ItemUtil.getItem("&9&lSummon Wolf", Material.BONE, 2,
                        new ArrayList<>(List.of("&7Drop this item to summon a", "&7wolf companion to fight for you")), false), "Prize",
                "WolfSummon")));

        possiblePrizes.add(new FreezeMobs(ItemUtil.NBTStamp(ItemUtil.getItem("&1&lFreeze Near Mobs", Material.ICE, 2,
                        new ArrayList<>(List.of("&7Drop this item to freeze", "&7all nearby mobs in place!")), false), "Prize",
                "FreezeMobs")));

        possiblePrizes.add(new HotHands(ItemUtil.getItem("&6&lHot Hands", Material.MAGMA_CREAM, 1,
                new ArrayList<>(List.of("", "&7Use this item as a weapon to", "&7set your enemy ablaze!")), false
                , Enchantment.FIRE_ASPECT)));

        possiblePrizes.add(new NotchApple(ItemUtil.getItem("&dEnchanted Golden Apple", Material.ENCHANTED_GOLDEN_APPLE, 1,
                null, false)));

        possiblePrizes.add(new TeamSlowDown(ItemUtil.NBTStamp(ItemUtil.getItem("&2&lTeam Slow Down", Material.TURTLE_SCUTE, 1,
                        new ArrayList<>(List.of("&7Drop this item to give your opponents", "&7a massive reduction in speed!")), false), "Prize",
                "TeamSlowDown")));

        possiblePrizes.add(new LevitationMembrane(ItemUtil.NBTStamp(ItemUtil.getItem("&b&lLevitation", Material.PHANTOM_MEMBRANE, 1,
                        new ArrayList<>(List.of("&7Drop this item to give your enemies", "&7levitation!")), false), "Prize",
                "LevitationMembrane")));

        possiblePrizes.add(new WitherRose(ItemUtil.NBTStamp(ItemUtil.getItem("&4&lWither Rose", Material.WITHER_ROSE, 1,
                        new ArrayList<>(List.of("&7Drop this item to give your enemies", "&7the wither effect!")), false), "Prize",
                "WitherRose")));

        possiblePrizes.add(new ChargedCreeperEgg(ItemUtil.NBTStamp(ItemUtil.getItem("&2&lCharged Creeper", Material.CREEPER_SPAWN_EGG, 1,
                new ArrayList<>(List.of("&7Drop this item to spawn a charged", "&7creeper to explode nearby mobs!")),
                false), "Prize", "ChargedCreeper")));
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

    public ArrayList<Prize> getPossiblePrizes() {
        return possiblePrizes;
    }
}
