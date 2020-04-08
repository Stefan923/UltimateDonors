package me.Stefan923.UltimateDonors.Listeners;

import me.Stefan923.UltimateDonors.UltimateDonors;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener, MessageUtils {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        FileConfiguration settings = UltimateDonors.getInstance().getSettingsManager().getConfig();

        if (player.hasPermission("ultimatedonors.use.emotes") && settings.getBoolean("Chat.Enable Emotes")) {
            event.setMessage(formatEmojis(settings, player, event.getMessage()));
        }

    }

}
