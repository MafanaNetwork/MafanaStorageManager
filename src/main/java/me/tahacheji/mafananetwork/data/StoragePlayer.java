package me.tahacheji.mafananetwork.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class StoragePlayer {

    private OfflinePlayer player;
    private World world;

    public StoragePlayer(OfflinePlayer player) {
        this.player = player;
    }

    public StoragePlayer(OfflinePlayer player, World world) {
        this.player = player;
        this.world = world;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }
}

