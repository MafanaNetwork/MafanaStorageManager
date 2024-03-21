package me.tahacheji.mafananetwork.data;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StoragePlayer {

    private final UUID uuid;
    private World world;

    public StoragePlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public StoragePlayer(UUID uuid, World world) {
        this.uuid = uuid;
        this.world = world;
    }

    public UUID getUuid() {
        return uuid;
    }

    public World getWorld() {
        return world;
    }
}

