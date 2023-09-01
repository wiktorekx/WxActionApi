package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;
import pl.wiktorekx.wxactionapi.plugin.placeholder.PlaceholderService;
import pl.wiktorekx.wxactionapi.plugin.utils.ChatUtils;

import java.util.Arrays;

public class SendMessageAction implements Action {

    private final PlaceholderService placeholderService;

    public SendMessageAction(PlaceholderService placeholderService) {
        this.placeholderService = placeholderService;
    }

    @Override
    @NotNull
    public String getName() {
        return "sendmessage";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        if (args.length == 0) throw new ActionException("Require 1 argument: message");
        boolean newLine = false;
        String firstLine = args[0];
        if (firstLine.startsWith("&nl")) {
            newLine = true;
            args[0] = firstLine.substring(3);
        }
        if (newLine) {
            Arrays.stream(args).forEach(line -> sendMessage(player, line));
        } else {
            sendMessage(player, String.join(" ", args));
        }
    }

    private void sendMessage(@Nullable Player player, String message) {
        if(player == null) {
            Bukkit.broadcastMessage(ChatUtils.color(message));
        } else {
            player.sendMessage(placeholderService.replace(player, ChatUtils.color(message)));
        }
    }
}
