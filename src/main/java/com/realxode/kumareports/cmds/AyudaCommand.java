package com.realxode.kumareports.cmds;

import com.realxode.kumareports.Main;
import com.realxode.kumareports.utils.DiscordWebhook;
import com.realxode.kumareports.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AyudaCommand implements CommandExecutor {

    private final Main main;

    public AyudaCommand(Main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("report")) {

            DiscordWebhook webhook = new DiscordWebhook(main.getUrl());
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (args.length == 0) {
                    Utils.sendMsg(player, main.getLang().getString("invalid-usage"));
                    return true;
                }

                // REPORTE

                if (args[0].equalsIgnoreCase("report")) {
                    if (args.length == 1) {
                        Utils.sendMsg(player, main.getLang().getString("Report.specify-player"));
                        return true;
                    }
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        Utils.sendMsg(player, main.getLang().getString("Report.invalid-player"));
                        return true;
                    }
                    if (args.length == 2) {
                        Utils.sendMsg(player, main.getLang().getString("Report.specify-reason"));
                        return true;
                    }
                    main.stringBuilderReport(args, webhook, target, sender);
                    Utils.sendMsg(player, main.getLang().getString("successfully-sent-webhook"));
                } else if (args[0].equalsIgnoreCase("bug")){
                    if (args.length == 1) {
                        Utils.sendMsg(player, main.getLang().getString("Bug.specify-bug-reason"));
                        return true;
                    }
                    main.stringBuilderBug(args, webhook, sender);
                    Utils.sendMsg(player, main.getLang().getString("successfully-sent-webhook"));
                } else {
                    if (args.length == 1) {
                        Utils.sendMsg(player, main.getLang().getString("Other.specify-reason"));
                        return true;
                    }
                    main.stringBuilderOtro(args, webhook, sender);

                    Utils.sendMsg(player, main.getLang().getString("successfully-sent-webhook"));
                }
                return true;
            }


            if (args.length == 0) {
                Utils.sendConsoleMsg(main.getLang().getString("invalid-usage"));
                return true;
            }
            if (args[0].equalsIgnoreCase("report")) {
                if (args.length == 1) {
                    Utils.sendConsoleMsg(main.getLang().getString("Report.specify-player"));
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    Utils.sendConsoleMsg(main.getLang().getString("Report.invalid-player"));
                    return true;
                }
                if (args.length == 2) {
                    Utils.sendConsoleMsg(main.getLang().getString("Report.specify-reason"));
                    return true;
                }
                main.stringBuilderReport(args, webhook, target, sender);
                Utils.sendConsoleMsg(main.getLang().getString("successfully-sent-webhook"));
            } else if (args[0].equalsIgnoreCase("bug")){
                if (args.length == 1) {
                    Utils.sendConsoleMsg(main.getLang().getString("Bug.specify-bug-reason"));
                    return true;
                }
                main.stringBuilderBug(args, webhook, sender);
                Utils.sendConsoleMsg(main.getLang().getString("successfully-sent-webhook"));
            } else {
                if (args.length == 1) {
                    Utils.sendConsoleMsg(main.getLang().getString("Other.specify-reason"));
                    return true;
                }
                main.stringBuilderOtro(args, webhook, sender);
                Utils.sendConsoleMsg(main.getLang().getString("successfully-sent-webhook"));
            }

        }


        return false;
    }

}
