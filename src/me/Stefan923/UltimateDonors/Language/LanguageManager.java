package me.Stefan923.UltimateDonors.Language;

import me.Stefan923.UltimateDonors.UltimateDonors;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LanguageManager implements MessageUtils {

    private static LanguageManager instance = new LanguageManager();
    private FileConfiguration config;
    private File cfile;
    private String languageFile;

    public static LanguageManager getInstance() {
        return instance;
    }

    public void setup(UltimateDonors p, String languageFile) {
        this.languageFile = languageFile;

        cfile = new File(p.getDataFolder(), "languages/" + languageFile);
        if (!cfile.exists()) {
            try {
                //noinspection ResultOfMethodCallIgnored
                File dir = new File(p.getDataFolder() + "/languages");

                if (!dir.exists())
                    dir.mkdir();

                cfile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileConfiguration settings = p.getSettingsManager().getConfig();

        config = YamlConfiguration.loadConfiguration(cfile);
        config.options().header("UltimateDonors by Stefan923.\n");
        for (String permission : settings.getStringList("Join Quit Messages.Group Permissions")) {
            String[] options = permission.split("\\.");
            if (options.length >= 1) {
                config.addDefault("Join Message." + options[options.length - 1], "&8(&3!&8) &a%playername% &fjoined the game!");
                config.addDefault("Quit Message." + options[options.length - 1], "&8(&3!&8) &a%playername% &fleft the game!");
            }
        }
        config.addDefault("Update Checker.Available", "&8(&3!&8) &fThere is a new version of &bUltimateDonors &favailable!\n&8(&3!&8) &fDownload link: &b%link%");
        config.addDefault("Update Checker.Not Available", "&8(&3!&8) &fThere's no update available for &bUltimateDonors&f.");
        config.options().copyDefaults(true);

        save();
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reset(UltimateDonors p) {
        FileConfiguration settings = p.getSettingsManager().getConfig();

        for (String permission : settings.getStringList("Join Quit Messages.Group Permissions")) {
            String[] options = permission.split(".");
            config.set("Join Message." + options[options.length - 1], "&8(&3!&8) &a%playername% &fjoined the game!");
            config.set("Quit Message." + options[options.length - 1], "&8(&3!&8) &a%playername% &fleft the game!");
        }
        config.set("Update Checker.Available", "&8(&3!&8) &fThere is a new version of &bUltimateDonors &favailable!\n&8(&3!&8) &fDownload link: &b%link%");
        config.set("Update Checker.Not Available", "&8(&3!&8) &fThere's no update available for &bUltimateDonors&f.");

        save();
    }

    public void save() {
        try {
            config.save(cfile);
        } catch (IOException e) {
            sendLogger(ChatColor.RED + "File '" + languageFile + "' couldn't be saved!");
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(cfile);
    }

    public String getLanguageFileName() {
        return languageFile;
    }

}
