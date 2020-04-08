package me.Stefan923.UltimateDonors.Commands.Type;

import me.Stefan923.UltimateDonors.Commands.AbstractCommand;
import me.Stefan923.UltimateDonors.UltimateDonors;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.Collections;
import java.util.List;

public class CommandUltimateDonors extends AbstractCommand implements MessageUtils {

    public CommandUltimateDonors() {
        super(null, false, "ultimatedonors");
    }

    @Override
    protected ReturnType runCommand(UltimateDonors instance, CommandSender sender, String... args) {
        sender.sendMessage(formatAll(" "));
        sendCenteredMessage(sender, formatAll("&8&m--+----------------------------------------+--&r"));
        sendCenteredMessage(sender, formatAll("&3&lUltimateDonors &f&lv" + instance.getDescription().getVersion()));
        sendCenteredMessage(sender, formatAll("&8&l» &fPlugin author: &bStefan923"));
        sendCenteredMessage(sender, formatAll(" "));
        sendCenteredMessage(sender, formatAll("&8&l» &fProvides an useful set of commands and utilities for donors."));
        sendCenteredMessage(sender, formatAll("&8&m--+----------------------------------------+--&r"));
        sender.sendMessage(formatAll(" "));

        return ReturnType.SUCCESS;
    }

    @Override
    protected List<String> onTab(UltimateDonors instance, CommandSender sender, String... args) {
        if (sender.hasPermission("ultimatedonors.admin"))
            return Collections.singletonList("reload");
        return null;
    }

    @Override
    public String getPermissionNode() {
        return null;
    }

    @Override
    public String getSyntax() {
        return "/ultimatedonors";
    }

    @Override
    public String getDescription() {
        return "Displays plugin info";
    }

}
