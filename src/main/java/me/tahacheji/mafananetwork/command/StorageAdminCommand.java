package me.tahacheji.mafananetwork.command;

import me.tahacheji.mafana.commandExecutor.Command;
import me.tahacheji.mafana.commandExecutor.paramter.Param;
import me.tahacheji.mafana.data.OfflineProxyPlayer;
import me.tahacheji.mafananetwork.MafanaStorageManager;
import me.tahacheji.mafananetwork.data.StoragePlayer;
import me.tahacheji.mafananetwork.data.WorldManager;
import org.bukkit.*;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class StorageAdminCommand {

    @Command(names = "msm", permission = "mafana.admin", playerOnly = true)
    public void teleportPlayerToStorage(Player x, @Param(name = "target") OfflineProxyPlayer target) {
        WorldManager worldManager = new WorldManager(UUID.fromString(target.getPlayerUUID()), "PlayerStorageTemplate");
        World playerWorld = worldManager.getPlayerWorld();
        Location teleportLocation = new Location(playerWorld, 0.3, 136, 1.3);
        x.teleport(teleportLocation);
    }
}
