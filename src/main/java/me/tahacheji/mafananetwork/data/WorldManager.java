package me.tahacheji.mafananetwork.data;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.util.FileUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;

public class WorldManager {

    private Player player;
    private String templateWorldName;

    public WorldManager(Player player, String templateWorldName) {
        this.player = player;
        this.templateWorldName = templateWorldName;
    }

    public World getPlayerWorld() {
        String playerWorldName = player.getName().toLowerCase() + "_storage_world";
        World existingWorld = Bukkit.getWorld(playerWorldName);

        if (existingWorld != null) {
            return existingWorld;
        }

        File worldFolder = new File(Bukkit.getWorldContainer().getParentFile(), playerWorldName);

        if (!worldFolder.exists()) {
            try {
                copyFolder(new File(templateWorldName), worldFolder);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        World newWorld = Bukkit.createWorld(new WorldCreator(playerWorldName));

        if (newWorld != null) {
            newWorld.setAutoSave(true);
        }

        return newWorld;
    }



    public void copyFolder(File source, File target) throws IOException {
        try {
            ArrayList<String> ignore = new ArrayList<>(Arrays.asList("uid.dat", "session.lock"));
            if(!ignore.contains(source.getName())) {
                if(source.isDirectory()) {
                    if(!target.exists())
                        if (!target.mkdirs())
                            throw new IOException("Couldn't create world directory!");
                    String files[] = source.list();
                    for (String file : files) {
                        File srcFile = new File(source, file);
                        File destFile = new File(target, file);
                        copyFolder(srcFile, destFile);
                    }
                } else {
                    InputStream in = new FileInputStream(source);
                    OutputStream out = new FileOutputStream(target);
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = in.read(buffer)) > 0)
                        out.write(buffer, 0, length);
                    in.close();
                    out.close();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public String getTemplateWorldName() {
        return templateWorldName;
    }
}
