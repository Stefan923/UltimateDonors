package me.Stefan923.UltimateDonors.Commands.Type;

import me.Stefan923.UltimateDonors.Commands.AbstractCommand;
import me.Stefan923.UltimateDonors.UltimateDonors;
import me.Stefan923.UltimateDonors.Utils.MessageUtils;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class CommandReload extends AbstractCommand implements MessageUtils {

    public CommandReload(AbstractCommand abstractCommand) {
        super(abstractCommand, false, "reload");
    }

    @Override
    protected ReturnType runCommand(UltimateDonors instance, CommandSender sender, String... args) {
        if (args.length != 2)
            return ReturnType.SYNTAX_ERROR;

        if (args[1].equalsIgnoreCase("all")) {
            instance.reloadSettingManager();
            instance.reloadLanguageManagers();
            sender.sendMessage(formatAll("&8[&3UltimateDonors&8] &fYou have successfully reloaded &ball &fmodules!"));
            return ReturnType.SUCCESS;
        }

        if (args[1].equalsIgnoreCase("settings")) {
            instance.reloadSettingManager();
            sender.sendMessage(formatAll("&8[&3UltimateDonors&8] &fYou have successfully reloaded &bsettings &fmodule!"));
            return ReturnType.SUCCESS;
        }

        if (args[1].equalsIgnoreCase("languages")) {
            instance.reloadLanguageManagers();
            sender.sendMessage(formatAll("&8[&3UltimateDonors&8] &fYou have successfully reloaded &blanguages &fmodule!"));
            return ReturnType.SUCCESS;
        }

        return ReturnType.SYNTAX_ERROR;
    }

    @Override
    protected List<String> onTab(UltimateDonors instance, CommandSender sender, String... args) {
        if (sender.hasPermission("ultimatedonors.admin"))
            return Arrays.asList("settings", "languages", "all");
        return null;
    }

    @Override
    public String getPermissionNode() {
        return "ultimatedonors.admin";
    }

    @Override
    public String getSyntax() {
        return "/ultimatedonors reload <all|settings|languages>";
    }

    @Override
    public String getDescription() {
        return "Reloads plugin settings.";
    }

}
