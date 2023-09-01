package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;
import pl.wiktorekx.wxactionapi.plugin.placeholder.PlaceholderService;

import java.util.Arrays;

public class SendCommandAction implements Action {

    private final PlaceholderService placeholderService;

    public SendCommandAction(PlaceholderService placeholderService) {
        this.placeholderService = placeholderService;
    }

    @Override
    @NotNull
    public String getName() {
        return "sendcommand";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        if(args.length > 1){
            String sender = args[0];
            CommandSender commandSender = player;
            if(sender.equalsIgnoreCase("console")){
                commandSender = Bukkit.getConsoleSender();
            }
            boolean op = sender.equalsIgnoreCase("op");
            boolean isOp = player.isOp();
            player.setOp(op);
            try {
                Bukkit.dispatchCommand(commandSender, placeholderService.replace(player, String.join(" ", Arrays.copyOfRange(args, 1, args.length))));
            } catch (Throwable e){
                throw new ActionException("", e);
            }
            if(op && !isOp) {
                player.setOp(false);
            }
        }
    }
}
