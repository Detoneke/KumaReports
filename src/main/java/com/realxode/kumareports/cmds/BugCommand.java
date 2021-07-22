package com.realxode.kumareports.cmds;

import com.realxode.kumareports.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

import static com.realxode.kumareports.utils.Utils.sendConsoleMsg;

public class BugCommand implements CommandExecutor {

    private Main main;
    public BugCommand(Main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            if(args.length == 0){
                sendConsoleMsg("&cInvalid usage: /bug <reason>");
                return true;
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < args.length; i++){
                stringBuilder.append(args[i]).append(" ");
            }
            Date date = new Date();
            main.getBugs().set("Bugs.from-console." + date.getTime() + ".bug", stringBuilder.toString());
            main.getBugs().save();
            sendConsoleMsg("Bug sended!");
            return true;
        }
        return false;
    }

}
