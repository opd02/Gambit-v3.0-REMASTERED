package me.opd.gambitremastered.commands.gteamGUI;

import me.opd.gambitremastered.GambitRemastered;
import me.opd.gambitremastered.game.TeamType;
import me.opd.gambitremastered.util.ItemUtil;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GTeamMenu extends Menu {


    public GTeamMenu() {

        this.setTitle("Team Selector");

        this.addButton(new Button(0) {
            @Override
            public ItemStack getItem() {
                return ItemUtil.getItem("&9&lBLUE TEAM", Material.BLUE_WOOL, 1, new ArrayList<>(List.of(
                        "&7Click this item join the blue team")), false);
            }

            @Override
            public void onClick(Player player) {
                GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(player, TeamType.BLUE);
            }
        });

        this.addButton(new Button(8) {
            @Override
            public ItemStack getItem() {
                return ItemUtil.getItem("&c&lRED TEAM", Material.RED_WOOL, 1, new ArrayList<>(List.of(
                        "&7Click this item join the red team")), false);
            }

            @Override
            public void onClick(Player player) {
                GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(player, TeamType.RED);
            }
        });

        this.addButton(new Button(4) {
            @Override
            public ItemStack getItem() {
                return ItemUtil.getItem("&7&lSPECTATE", Material.LIGHT_GRAY_WOOL, 1, new ArrayList<>(List.of(
                        "&7Click this item join the spectator team")), false);
            }

            @Override
            public void onClick(Player player) {
                GambitRemastered.gameSession.getPlayerManager().addPlayerToTeam(player, TeamType.SPECTATE);
            }
        });
    }
}