package me.tahacheji.mafananetwork.events;

import me.tahacheji.mafananetwork.MafanaStorageManager;
import me.tahacheji.mafananetwork.data.StoragePlayer;
import me.tahacheji.mafananetwork.data.WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
    }
}
