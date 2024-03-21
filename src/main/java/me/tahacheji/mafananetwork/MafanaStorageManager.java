package me.tahacheji.mafananetwork;

import me.tahacheji.mafana.commandExecutor.Command;
import me.tahacheji.mafana.commandExecutor.CommandHandler;
import me.tahacheji.mafananetwork.command.StorageAdminCommand;
import me.tahacheji.mafananetwork.command.StorageCommand;
import me.tahacheji.mafananetwork.data.StoragePlayer;
import me.tahacheji.mafananetwork.events.PlayerEdit;
import me.tahacheji.mafananetwork.events.PlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class MafanaStorageManager extends JavaPlugin {

    private static MafanaStorageManager instance;
    private List<StoragePlayer> storagePlayerList = new ArrayList<>();
    private List<UUID> ignorePlayer = new ArrayList<>();
    private MafanaStorageManager mafanaStorageManager;


    @Override
    public void onEnable() {
        instance = this;
        mafanaStorageManager = this;
        CommandHandler.registerCommands(StorageCommand.class, this);
        CommandHandler.registerCommands(StorageAdminCommand.class, this);
        getServer().getPluginManager().registerEvents(new PlayerEdit(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
    }

    public StoragePlayer getStoragePlayer(UUID uuid) {
        for (StoragePlayer storagePlayer : getStoragePlayerList()) {
            if (storagePlayer.getUuid().toString().equalsIgnoreCase(uuid.toString())) {
                return storagePlayer;
            }
        }
        return null;
    }

    public void removeStoragePlayer(UUID uuid) {
        Iterator<StoragePlayer> iterator = storagePlayerList.iterator();

        while (iterator.hasNext()) {
            StoragePlayer storagePlayer = iterator.next();
            if (storagePlayer.getUuid().equals(uuid)) {
                Bukkit.unloadWorld(storagePlayer.getWorld(), true);
                iterator.remove();
                break;
            }
        }
    }

    public static MafanaStorageManager getInstance() {
        return instance;
    }

    public List<UUID> getIgnorePlayer() {
        return ignorePlayer;
    }

    public List<StoragePlayer> getStoragePlayerList() {
        return storagePlayerList;
    }

    public MafanaStorageManager getMafanaStorageManager() {
        return mafanaStorageManager;
    }
}
