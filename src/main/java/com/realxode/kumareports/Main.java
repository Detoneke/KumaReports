package com.realxode.kumareports;

import com.realxode.kumareports.cmds.AyudaCommand;
import com.realxode.kumareports.utils.DiscordWebhook;
import com.realxode.kumareports.utils.File;
import com.realxode.kumareports.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.realxode.kumareports.utils.Utils.sendConsoleMsg;

public final class Main extends JavaPlugin {

    private final PluginDescriptionFile pluginyml = getDescription();
    public String version = pluginyml.getVersion();
    public final File lang = new File(this, "lang");
    public String url;
    public Utils utils;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        utils = new Utils(this);
        url = this.getConfig().getString("webhook-url");
        DiscordWebhook webhook = new DiscordWebhook(url);
        webhook.setContent(this.getConfig().getString("start-message-webhook"));
        if (this.getConfig().getString("webhook-url").contains("INSERT-URL")) {
            sendConsoleMsg("&7Check the configuration and change the &n\"webhook-url\"&7 parameter to the url of your webhook.");
        } else {
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.getCommand("report").setExecutor(new AyudaCommand(this));
                sendConsoleMsg("&6Plugin enabled succesfully! Currently plugin version: &e" + version);
            } catch (Exception e) {
                e.printStackTrace();
                sendConsoleMsg("&7Error trying to connect with webhook.");
            }
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getLang() {
        return lang;
    }

    public String getUrl() {
        return url;
    }

    public void stringBuilderReport(String[] args, DiscordWebhook webhook, Player target, CommandSender sender) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        if(!(sender instanceof Player)) {
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(this.getLang().getString("Webhook.title").replace("%player%", "Console"))
                    .setDescription(this.getLang().getString("Webhook.description").replace("%player%", "Console"))
                    .addField(this.getLang().getString("Webhook.field-type"), this.getLang().getString("Webhook.field-report"), true)
                    .addField(this.getLang().getString("Webhook.field-getreported"), target.getName(), true)
                    .addField(this.getLang().getString("Webhook.field-reason"), stringBuilder.toString(), false)
                    .setColor(Color.RED).setFooter(formatter.format(date), this.getConfig().getString("icon-url")));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Player player = (Player) sender;
            this.getUtils().setCooldown(player);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(this.getLang().getString("Webhook.title").replace("%player%", player.getName()))
                    .setDescription(this.getLang().getString("Webhook.description").replace("%player%", player.getName()))
                    .addField(this.getLang().getString("Webhook.field-type"), this.getLang().getString("Webhook.field-report"), true)
                    .addField(this.getLang().getString("Webhook.field-getreported"), target.getName(), true)
                    .addField(this.getLang().getString("Webhook.field-reason"), stringBuilder.toString(), false)
                    .setColor(Color.RED).setFooter(formatter.format(date), this.getConfig().getString("icon-url")));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void stringBuilderBug(String[] args, DiscordWebhook webhook, CommandSender sender) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        if(!(sender instanceof Player)) {
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(this.getLang().getString("Webhook.title").replace("%player%", "Console"))
                    .setDescription(this.getLang().getString("Webhook.description").replace("%player%", "Console"))
                    .addField(this.getLang().getString("Webhook.field-type"), this.getLang().getString("Webhook.field-bug"), true)
                    .addField(this.getLang().getString("Webhook.field-reason"), stringBuilder.toString(), false)
                    .setColor(Color.YELLOW).setFooter(formatter.format(date), this.getConfig().getString("icon-url")));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Player player = (Player) sender;
            this.getUtils().setCooldown(player);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(this.getLang().getString("Webhook.title").replace("%player%", player.getName()))
                    .setDescription(this.getLang().getString("Webhook.description").replace("%player%", player.getName()))
                    .addField(this.getLang().getString("Webhook.field-type"), this.getLang().getString("Webhook.field-bug"), true)
                    .addField(this.getLang().getString("Webhook.field-reason"), stringBuilder.toString(), false)
                    .setColor(Color.YELLOW).setFooter(formatter.format(date), this.getConfig().getString("icon-url")));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void stringBuilderOtro(String[] args, DiscordWebhook webhook, CommandSender sender) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            stringBuilder.append(args[i]).append(" ");
        }
        if(!(sender instanceof Player)) {
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(this.getLang().getString("Webhook.title").replace("%player%", "Console"))
                    .setDescription(this.getLang().getString("Webhook.description").replace("%player%", "Console"))
                    .addField(this.getLang().getString("Webhook.field-type"), this.getLang().getString("Webhook.field-other"), true)
                    .addField(this.getLang().getString("Webhook.field-reason"), stringBuilder.toString(), false)
                    .setColor(Color.GREEN).setFooter(formatter.format(date), this.getConfig().getString("icon-url")));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Player player = (Player) sender;
            this.getUtils().setCooldown(player);
            webhook.addEmbed(new DiscordWebhook.EmbedObject()
                    .setTitle(this.getLang().getString("Webhook.title").replace("%player%", player.getName()))
                    .setDescription(this.getLang().getString("Webhook.description").replace("%player%", player.getName()))
                    .addField(this.getLang().getString("Webhook.field-type"), this.getLang().getString("Webhook.field-other"), true)
                    .addField(this.getLang().getString("Webhook.field-reason"), stringBuilder.toString(), false)
                    .setColor(Color.GREEN).setFooter(formatter.format(date), this.getConfig().getString("icon-url")));
            try {
                webhook.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Utils getUtils(){
        return utils;
    }

}



/*
webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle("Ayuda - Consola")
                .setDescription("La consola pidio ayuda, probablemente sea un testeo.")
                .addField("Tipo:", "Reporte", true)
                .addField("Jugador reportado:", "Juan 25", true)
                .addField("Razon:", "Intento de jakeo uwu", true).setColor(Color.RED));
                try {
                    webhook.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
 */
