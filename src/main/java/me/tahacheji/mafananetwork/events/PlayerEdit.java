package me.tahacheji.mafananetwork.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlayerEdit implements Listener {


    @EventHandler
    public void onSignChange(SignChangeEvent event) {
        if (event.getPlayer() != null) {
            if(event.getPlayer().isOp() || event.getPlayer().getGameMode() == GameMode.CREATIVE) {
                return;
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        EntityType entityType = event.getRightClicked().getType();

        // Check if the clicked entity is one of the protected types
        if (entityType == EntityType.ITEM_FRAME ||
                entityType == EntityType.PAINTING) {
            // Prevent players from breaking the protected entity
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        // Check if the block being broken is a sign
        if (event.getBlock().getType() == Material.OAK_SIGN ||
                event.getBlock().getType() == Material.OAK_HANGING_SIGN ||
                event.getBlock().getType() == Material.OAK_WALL_SIGN) {
            // Prevent players from breaking the sign
            event.setCancelled(true);
        }
    }
}
