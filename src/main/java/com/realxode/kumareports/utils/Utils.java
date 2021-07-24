package com.realxode.kumareports.utils;

import com.realxode.kumareports.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Utils {

    private static Main main;
    private final List<UUID> cooldown;
    public Utils(Main main){
        Utils.main = main;
        cooldown = new ArrayList<>();
    }

    public static void sendConsoleMsg(String message){
        String prefix = main.getLang().getString("ingame-prefix");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                prefix + message));
    }

    public static void sendMsg(Player player, String message){
        String prefix = main.getLang().getString("ingame-prefix");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public List<UUID> getCooldown() {
        return cooldown;
    }

    public void setCooldown(Player player){
        if (getCooldown().contains(player.getUniqueId())){
            Utils.sendMsg(player, "¡Tienes cooldown! Espera 30 segundos para volver a usar el comando.");
            return;
        }
        if (!getCooldown().contains(player.getUniqueId())) {
            Bukkit.getScheduler().runTaskLater(main, () -> getCooldown().remove(player.getUniqueId()), 30 * 20);
        }

    }
}
