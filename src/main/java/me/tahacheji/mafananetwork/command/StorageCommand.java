package me.tahacheji.mafananetwork.command;

import me.tahacheji.mafana.commandExecutor.Command;
import me.tahacheji.mafananetwork.MafanaStorageManager;
import me.tahacheji.mafananetwork.data.StoragePlayer;
import me.tahacheji.mafananetwork.data.WorldManager;
import org.bukkit.*;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StorageCommand {

    @Command(names = "storage", permission = "mafana.copper", playerOnly = true)
    public void teleportPlayerToStorage(Player x) {
        if (!MafanaStorageManager.getInstance().getMafanaStorageManager().getIgnorePlayer().contains(x.getUniqueId())) {
            StoragePlayer storagePlayer = MafanaStorageManager.getInstance().getMafanaStorageManager().getStoragePlayer(x.getUniqueId());

            if (storagePlayer == null) {
                WorldManager worldManager = new WorldManager(x.getUniqueId(), "PlayerStorageTemplate");
                World playerWorld = worldManager.getPlayerWorld();

                if (playerWorld != null) {
                    storagePlayer = new StoragePlayer(x.getUniqueId(), playerWorld);
                    MafanaStorageManager.getInstance().getMafanaStorageManager().getStoragePlayerList().add(storagePlayer);
                }
            }

            if (storagePlayer != null && Bukkit.getOfflinePlayer(storagePlayer.getUuid()).isOnline()) {
                Player player = Bukkit.getOfflinePlayer(storagePlayer.getUuid()).getPlayer();
                World playerWorld = storagePlayer.getWorld();

                if (playerWorld != null) {
                    Location teleportLocation = new Location(playerWorld, 0.3, 136, 1.3);
                    player.teleport(teleportLocation);
                } else {
                    x.sendMessage(ChatColor.RED + "MafanaStorageManager: Template world not found.");
                }
            } else {
                x.sendMessage(ChatColor.RED + "MafanaStorageManager: Player not found.");
            }
        } else {
            x.sendMessage(ChatColor.RED + "MafanaStorageManager: You cannot teleport to storage.");
        }
    }
}
