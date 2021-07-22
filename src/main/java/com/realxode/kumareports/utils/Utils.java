package com.realxode.kumareports.utils;

import com.realxode.kumareports.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    private static Main main;
    public Utils(Main main){
        this.main = main;
    }

    public static void sendConsoleMsg(String message){
        String prefix = main.getLang().getString("prefix");
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                prefix + message));
    }

    public static void sendMsg(Player player, String message){
        String prefix = main.getLang().getString("prefix");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

}
