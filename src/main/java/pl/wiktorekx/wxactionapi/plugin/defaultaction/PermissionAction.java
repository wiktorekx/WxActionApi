package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

import java.util.Arrays;

public class PermissionAction implements Action {
    private final ActionService actionService;

    public PermissionAction(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    @NotNull
    public String getName() {
        return "permission";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        if(args.length > 1) {
            if(player.hasPermission(args[0])) {
                actionService.execAction(player, Arrays.copyOfRange(args, 1, args.length));
            }
        }
    }
}
