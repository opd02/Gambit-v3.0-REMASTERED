package me.opd.gambitremastered.prizes;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.util.ChatUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

public class PrizeCrateInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getClickedBlock() == null)
            return;

        Block block = e.getClickedBlock();
        if (!block.getBlockData().getMaterial().equals(Material.ENDER_CHEST))
            return;

        e.setCancelled(true);

        ItemStack item = p.getInventory().getItemInMainHand();
        if (item.getType().equals(Material.AIR))
            return;

        if (item.hasItemMeta() && item.getType().equals(Material.SUNFLOWER)) {
            GambitRemastered.prizeManager.givePlayerRandomPrize(p);

            int num = p.getInventory().getItemInMainHand().getAmount() - 1;
            p.getInventory().getItemInMainHand().setAmount(num);
            Location fwl = new Location(p.getWorld(), block.getX() + 0.5, block.getY() + 1, block.getZ() + 0.5);
            Firework fw = p.getWorld().spawn(fwl, Firework.class);
            FireworkMeta fm = fw.getFireworkMeta();
            fm.setPower(0);
            fm.addEffect(FireworkEffect.builder().withColor(Color.YELLOW).withFade(Color.RED).with(FireworkEffect.Type.BALL).build());
            fw.setFireworkMeta(fm);
            fw.detonate();
            p.playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, (float) 1, (float) 1);

        } else {
            p.sendMessage(ChatUtil.prefix + ChatUtil.format("&cThat is not a spin token!"));
            p.playSound(p.getLocation(), Sound.ITEM_SHIELD_BREAK, (float) 0.5, (float) 0.5);
        }
    }

}
