package me.Stefan923.UltimateDonors;

import me.Stefan923.UltimateDonors.Commands.CommandManager;
import me.Stefan923.UltimateDonors.Language.LanguageManager;
import me.Stefan923.UltimateDonors.Listeners.AsyncPlayerChatListener;
import me.Stefan923.UltimateDonors.Listeners.PlayerJoinListener;
import me.Stefan923.UltimateDonors.Listeners.PlayerQuitListener;
import me.Stefan923.UltimateDonors.Settings.SettingsManager;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import me.Stefan923.UltimateDonors.Utils.Metrics;
import me.Stefan923.UltimateDonors.Utils.VersionUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class UltimateDonors extends JavaPlugin implements MessageUtils, VersionUtils {
    private static UltimateDonors instance;

    private SettingsManager settingsManager;
    private HashMap<String, LanguageManager> languageManagers;
    private CommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;

        settingsManager = SettingsManager.getInstance();
        settingsManager.setup(this);

        languageManagers = new HashMap<>();
        for (String fileName : settingsManager.getConfig().getStringList("Languages.Available Languages")) {
            LanguageManager languageManager = new LanguageManager();
            fileName = fileName.toLowerCase();
            languageManager.setup(this, fileName);
            languageManagers.put(fileName, languageManager);
        }

        Metrics pluginMetrics = new Metrics(this, 7015);

        sendLogger("&8&l> &7&m------- &8&l( &3&lUltimateDonors &b&lby Stefan923 &8&l) &7&m------- &8&l<");
        sendLogger("&b   Plugin has been initialized.");
        sendLogger("&b   Version: &3v" + getDescription().getVersion());
        sendLogger("&b   Enabled listeners: &3" + enableListeners());
        sendLogger("&b   Enabled commands: &3" + enableCommands());
        sendLogger("&8&l> &7&m------- &8&l( &3&lUltimateDonors &b&lby Stefan923 &8&l) &7&m------- &8&l<");

        if (settingsManager.getConfig().getBoolean("Update Checker.Enable.On Plugin Enable"))
            Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
                checkForUpdate(this, this);
            });
    }

    public static UltimateDonors getInstance() {
        return instance;
    }

    private Integer enableListeners() {
        Integer i = 4;
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(this), this);
        pluginManager.registerEvents(new PlayerQuitListener(), this);
        pluginManager.registerEvents(new AsyncPlayerChatListener(), this);
        return i;
    }

    private Integer enableCommands() {
        commandManager = new CommandManager(this);
        return commandManager.getCommands().size();
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }

    public void reloadSettingManager() {
        settingsManager.reload();
    }

    public LanguageManager getLanguageManager(String language) {
        return languageManagers.get(language);
    }

    public HashMap<String, LanguageManager> getLanguageManagers() {
        return languageManagers;
    }

    public void reloadLanguageManagers() {
        languageManagers.clear();
        for (String fileName : settingsManager.getConfig().getStringList("Languages.Available Languages")) {
            LanguageManager languageManager = new LanguageManager();
            fileName = fileName.toLowerCase();
            languageManager.setup(this, fileName);
            languageManagers.put(fileName, languageManager);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

}
