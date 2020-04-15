package me.Stefan923.UltimateDonors.Settings;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SettingsManager {

    private static SettingsManager instance = new SettingsManager();
    private FileConfiguration config;
    private File cfile;

    public static SettingsManager getInstance() {
        return instance;
    }

    public void setup(Plugin p) {
        cfile = new File(p.getDataFolder(), "settings.yml");
        config = YamlConfiguration.loadConfiguration(cfile);
        config.options().header("UltimateDonors by Stefan923\n");
        config.addDefault("Languages.Default Language", "lang_en.yml");
        config.addDefault("Languages.Available Languages", Arrays.asList("lang_en.yml"));
        config.addDefault("Chat.Enable Emotes", true);
        config.addDefault("Chat.Emotes.:shrug:", "¯\\_(ツ)_/¯");
        config.addDefault("Chat.Emotes.:tableflip:", "(╯°□°）╯︵┻━┻");
        config.addDefault("Chat.Emotes.:star:", "✮");
        config.addDefault("Chat.Emotes.<3", "§c❤");
        config.addDefault("Chat.Emotes.o/", "( ﾟ◡ﾟ)/");
        config.addDefault("Chat.Emotes.:totem:", "⦿_⦿");
        config.addDefault("Enabled Commands.Emotes", true);
        config.addDefault("Join Quit Messages.Enable Join Message", true);
        config.addDefault("Join Quit Messages.Enable Quit Message", true);
        config.addDefault("Join Quit Messages.Group Permissions", Arrays.asList("ultimatedonors.jqmessages.vip", "ultimatedonors.jqmessages.mvp"));
        config.addDefault("Update Checker.Enable.On Plugin Enable", true);
        config.addDefault("Update Checker.Enable.On Join", true);
        config.options().copyDefaults(true);
        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void resetConfig() {
        config.set("Languages.Default Language", "lang_en.yml");
        config.set("Languages.Available Languages", Arrays.asList("lang_en.yml"));
        config.set("Chat.Enable Emojis", true);
        config.set("Chat.Emojis.:shrug:", "¯\\_(ツ)_/¯");
        config.set("Chat.Emojis.:tableflip:", "(╯°□°）╯︵┻━┻");
        config.set("Chat.Emojis.:star:", "✮");
        config.set("Chat.Emojis.<3", "§c❤");
        config.set("Chat.Emojis.o/", "( ﾟ◡ﾟ)/");
        config.set("Chat.Emojis.:totem:", "⦿_⦿");
        config.set("Enabled Commands.Emotes", true);
        config.set("Join Quit Messages.Enable Join Message", true);
        config.set("Join Quit Messages.Enable Quit Message", true);
        config.set("Join Quit Messages.Group Permissions", Arrays.asList("ultimatedonors.jqmessages.vip", "ultimatedonors.jqmessages.mvp"));
        config.set("Update Checker.Enable.On Plugin Enable", true);
        config.set("Update Checker.Enable.On Join", true);
        save();
    }

    private void save() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            Bukkit.getLogger().severe(ChatColor.RED + "File 'settings.yml' couldn't be saved!");
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

}
