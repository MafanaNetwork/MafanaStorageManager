package me.tahacheji.mafananetwork;

import me.tahacheji.mafananetwork.command.StorageCommand;
import me.tahacheji.mafananetwork.data.StoragePlayer;
import me.tahacheji.mafananetwork.events.PlayerEdit;
import me.tahacheji.mafananetwork.events.PlayerJoin;
import me.tahacheji.mafananetwork.events.PlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class MafanaStorageManager extends JavaPlugin {

    private List<StoragePlayer> storagePlayerList = new ArrayList<>();
    private static MafanaStorageManager mafanaStorageManager;


    @Override
    public void onEnable() {
        mafanaStorageManager = this;
        getServer().getPluginCommand("storage").setExecutor(new StorageCommand());
        getServer().getPluginManager().registerEvents(new PlayerEdit(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
    }

    @Override
    public void onDisable() {

    }

    public StoragePlayer getStoragePlayer(OfflinePlayer player) {
        for (StoragePlayer storagePlayer : getStoragePlayerList()) {
            if (storagePlayer.getPlayer().getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                return storagePlayer;
            }
        }
        return null;
    }

    public void removeStoragePlayer(OfflinePlayer player) {
        Iterator<StoragePlayer> iterator = storagePlayerList.iterator();

        while (iterator.hasNext()) {
            StoragePlayer storagePlayer = iterator.next();
            if (storagePlayer.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                Bukkit.unloadWorld(storagePlayer.getWorld(), true);
                iterator.remove(); // Remove the matching StoragePlayer
                break; // Optional: If you only want to remove one matching player, break the loop.
            }
        }
    }

    public List<StoragePlayer> getStoragePlayerList() {
        return storagePlayerList;
    }

    public static MafanaStorageManager getMafanaStorageManager() {
        return mafanaStorageManager;
    }
}
