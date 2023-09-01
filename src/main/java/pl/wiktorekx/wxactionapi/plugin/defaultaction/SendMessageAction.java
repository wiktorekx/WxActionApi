package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
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
    public void onAction(@Nullable Player player, @NotNull String[] args) {
        boolean newLine = false;
        if(args.length > 0) {
            String firstLine = args[0];
            if(firstLine.startsWith("&nl")) {
                newLine = true;
                args[0] = firstLine.substring(3);
            }
            if(newLine) {
                Arrays.stream(args).forEach(line -> player.sendMessage(placeholderService.replace(player, ChatUtils.color(line))));
            } else {
                player.sendMessage(placeholderService.replace(player, ChatUtils.color(String.join(" ", args))));
            }
        }
    }
}
