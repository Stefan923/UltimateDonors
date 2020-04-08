package me.Stefan923.UltimateDonors.Listeners;

import me.Stefan923.UltimateDonors.UltimateDonors;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener, MessageUtils {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        UltimateDonors instance = UltimateDonors.getInstance();
        FileConfiguration settings = instance.getSettingsManager().getConfig();

        event.setJoinMessage("");

        if (settings.getBoolean("Join Quit Messages.Enable Join Message")) {
            for (String permission : settings.getStringList("Join Quit Messages.Group Permissions")) {
                if (player.hasPermission(permission)) {
                    String[] options = permission.split("\\.");
                    if (options.length >= 1) {
                        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                            onlinePlayer.sendMessage(prepareMessageByLang(onlinePlayer, "Join Message." + options[options.length - 1]).replace("%playername%", player.getName()));
                        }
                    }
                    return;
                }
            }
        }

    }

}
