package br.com.mitizuky.mitiHakai;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class MitiHakai extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "MitiHakai " + ChatColor.DARK_GREEN + "iniciado.");
        getCommand("hakai").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.DARK_PURPLE + "MitiHakai " + ChatColor.RED + "desligado.");
    }
}
