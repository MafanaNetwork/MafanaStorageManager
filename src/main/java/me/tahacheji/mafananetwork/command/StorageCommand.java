package me.tahacheji.mafananetwork.command;

import me.tahacheji.mafananetwork.MafanaStorageManager;
import me.tahacheji.mafananetwork.data.StoragePlayer;
import me.tahacheji.mafananetwork.data.WorldManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StorageCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (label.equalsIgnoreCase("storage")) {
            Player x = (Player) sender;
            StoragePlayer storagePlayer = MafanaStorageManager.getMafanaStorageManager().getStoragePlayer(x);

            if (storagePlayer == null) {
                WorldManager worldManager = new WorldManager(x, "PlayerStorageTemplate");
                World playerWorld = worldManager.getPlayerWorld();

                if (playerWorld != null) {
                    storagePlayer = new StoragePlayer(x, playerWorld);
                    MafanaStorageManager.getMafanaStorageManager().getStoragePlayerList().add(storagePlayer);
                }
            }

            if (storagePlayer != null && storagePlayer.getPlayer().isOnline()) {
                Player player = storagePlayer.getPlayer().getPlayer();
                World playerWorld = storagePlayer.getWorld();

                if (playerWorld != null) {
                    Location teleportLocation = new Location(playerWorld, 0.3, 136, 1.3);
                    player.teleport(teleportLocation);
                } else {
                    sender.sendMessage(ChatColor.RED + "MafanaStorageManager: Template world not found.");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "MafanaStorageManager: Player not found.");
            }
        }
        return true;
    }
}
