package me.tahacheji.mafananetwork.events;

import me.tahacheji.mafananetwork.MafanaStorageManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeave implements Listener {


    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        MafanaStorageManager.getInstance().getMafanaStorageManager().removeStoragePlayer(event.getPlayer().getUniqueId());
    }

}
