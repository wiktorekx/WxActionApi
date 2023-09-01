package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public class ForOnlinePlayersAction implements Action {
    private final ActionService actionService;

    public ForOnlinePlayersAction(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    @NotNull
    public String getName() {
        return "foronlineplayers";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        if (args.length == 0) throw new ActionException("Require 1 argument: action");
        for(Player p : Bukkit.getOnlinePlayers()) {
            actionService.execAction(p, args);
        }
    }
}
