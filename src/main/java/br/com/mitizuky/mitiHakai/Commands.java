package br.com.mitizuky.mitiHakai;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class Commands implements CommandExecutor {

    private HashMap<UUID, Long> cooldowns = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("hakai")) {

            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;

                if (!player.hasPermission("miti.hakai")) {
                    player.sendMessage(ChatColor.RED + "Você não tem permissão para usar este comando.");
                    return false;
                }

                UUID playerUUID = player.getUniqueId();
                long currentTime = System.currentTimeMillis();

                if (cooldowns.containsKey(playerUUID)) {
                    long lastUsedTime = cooldowns.get(playerUUID);
                    long cooldownTime = 10 * 60 * 1000;
                    long timeLeft = (lastUsedTime + cooldownTime) - currentTime;

                    if (timeLeft > 0) {
                        long minutesLeft = timeLeft / 60000;
                        player.sendMessage(ChatColor.RED + "Você precisa esperar " + minutesLeft + " minutos para usar o comando novamente.");
                        return false;
                    }
                }

                if (args.length < 1) {
                    player.sendMessage(ChatColor.RED + "Uso: /hakai <jogador>");
                    return false;
                }

                String targetPlayerName = args[0];
                Player targetPlayer = JavaPlugin.getPlugin(MitiHakai.class).getServer().getPlayer(targetPlayerName);

                if (targetPlayer == null) {
                    player.sendMessage(ChatColor.RED + "Jogador não encontrado!");
                    return false;
                }
                else {
                    targetPlayer.setHealth(0);
                    Bukkit.broadcastMessage(ChatColor.GOLD + "O jogador " + ChatColor.WHITE + player.getName() + ChatColor.GOLD + " usou um " + ChatColor.DARK_PURPLE + ChatColor.BOLD + "hakai " + ChatColor.GOLD + "em " + ChatColor.WHITE + targetPlayer.getName() + "!");

                    cooldowns.put(playerUUID, currentTime);
                    return false;
                }
            }
        }


        return false;
    }
}
