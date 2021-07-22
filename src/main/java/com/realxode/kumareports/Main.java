package com.realxode.kumareports;

import com.realxode.kumareports.cmds.BugCommand;
import com.realxode.kumareports.cmds.ReportCommand;
import com.realxode.kumareports.utils.File;
import com.realxode.kumareports.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import javax.rmi.CORBA.Util;

import static com.realxode.kumareports.utils.Utils.sendConsoleMsg;

public final class Main extends JavaPlugin {

    private PluginDescriptionFile pluginyml = getDescription();
    public String version = pluginyml.getVersion();
    public final File reports = new File(this, "reports");
    public final File bugs = new File(this, "bugs");
    public final File lang = new File(this, "lang");

    @Override
    public void onEnable() {
        Utils utils = new Utils(this);
        sendConsoleMsg("Trying to &7&l&nLOAD&f config.");
        try {
            this.saveDefaultConfig();
        } catch (Exception e){
            e.printStackTrace();
        }
        sendConsoleMsg("Trying to &7&l&nLOAD&f commands.");
        if(this.getConfig().getBoolean("Modules.report")) {
            this.getCommand("report").setExecutor(new ReportCommand(this));
            sendConsoleMsg("Module &2&nREPORT&f loaded.");
        }
        if(this.getConfig().getBoolean("Modules.bug")) {
            this.getCommand("bug").setExecutor(new BugCommand(this));
            sendConsoleMsg("Module &2&nBUG&f loaded.");
        }
        if(!this.getConfig().getBoolean("Modules.report") && (!this.getConfig().getBoolean("Modules.bug"))) {
            sendConsoleMsg("No modules loaded, plugin disabled.");
            Bukkit.getPluginManager().disablePlugin(this);
        }



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getReports() {
        return reports;
    }

    public File getBugs() {
        return bugs;
    }

    public File getLang() {
        return lang;
    }
}
