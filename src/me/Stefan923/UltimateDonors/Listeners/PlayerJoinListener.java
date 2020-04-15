package me.Stefan923.UltimateDonors.Listeners;

import me.Stefan923.UltimateDonors.UltimateDonors;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener, MessageUtils {

    private UltimateDonors instance;

    public PlayerJoinListener(UltimateDonors instance) {
        this.instance = instance;
    }

    @EventHandler (priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        FileConfiguration settings = instance.getSettingsManager().getConfig();

        event.setJoinMessage("");
        Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
            @Override
            public void run() {
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
        },  2 * 20L);

    }

}
