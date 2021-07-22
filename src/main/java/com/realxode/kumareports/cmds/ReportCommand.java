package com.realxode.kumareports.cmds;

import com.realxode.kumareports.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.Iterator;

import static com.realxode.kumareports.utils.Utils.sendConsoleMsg;
import static com.realxode.kumareports.utils.Utils.sendMsg;

public class ReportCommand implements CommandExecutor {

    private final Main main;

    public ReportCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sendConsoleMsg(main.getLang().getString("Report.invalid-usage"));
                return true;
            }
            if (args.length == 1) {
                sendConsoleMsg(main.getLang().getString("Report.invalid-usage"));
                return true;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                stringBuilder.append(args[i]).append(" ");
            }
            String target = args[0];
            Date date = new Date();
            main.getReports().set("Reports.from-console." + date.getTime() + ".reported", target);
            main.getReports().set("Reports.from-console." + date.getTime() + ".reason", stringBuilder.toString());
            main.getReports().save();
            sendConsoleMsg(main.getLang().getString("Report.report-done"));
            for (Player staff : Bukkit.getOnlinePlayers()){
                if (staff.hasPermission("kumareports.staff") || (staff.isOp())){
                    String format = main.getConfig().getString("Report.report-format-in-chat");
                    staff.sendMessage(format.replace("%reporter%", "Console")
                            .replace("%reported%", target)
                            .replace("%reason%", stringBuilder.toString()));
                }
            }
            return true;
        } else {
            Player player = (Player) sender;
            if (args.length == 0) {
                sendMsg(player, main.getLang().getString("Report.invalid-usage"));
                return true;
            }
            if (args.length == 1) {
                sendMsg(player, main.getLang().getString("Report.invalid-usage"));
                return true;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                stringBuilder.append(args[i]).append(" ");
            }
            String target = args[0];
            Date date = new Date();
            main.getReports().set("Reports." + player.getName() + " - " + date.getTime() + ".reported", target);
            main.getReports().set("Reports." + player.getName() + " - " + date.getTime() + ".reason", stringBuilder.toString());
            main.getReports().save();
            sendMsg(player, main.getLang().getString("Report.report-done"));
            Iterator var10 = Bukkit.getOnlinePlayers().iterator();
            while (var10.hasNext()){
                Player players = (Player)var10.next();
                if (players.hasPermission("kumareports.staff")){
                    String format = main.getLang().getString("Report.format");
                    players.sendMessage(format.replace("%reporter%", player.getName())
                            .replace("%reported%", target)
                            .replace("%reason%", stringBuilder.toString()));
                }
            }
        }
        return false;
    }

}
