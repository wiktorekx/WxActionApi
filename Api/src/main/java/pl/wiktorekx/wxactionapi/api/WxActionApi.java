package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

import java.util.Objects;

public class WxActionApi {
    private static ActionService actionService;

    private WxActionApi() {
        throw new RuntimeException();
    }

    public static void setActionService(ActionService actionService) {
        if(WxActionApi.actionService != null) throw new RuntimeException();
        WxActionApi.actionService = actionService;
    }

    @NotNull
    public static ActionService getActionService() {
        return Objects.requireNonNull(actionService);
    }

    public static void registerAction(@NotNull Action action) {
        getActionService().registerAction(action);
    }

    public static  void unregisterAction(@NotNull String name) {
        getActionService().unregisterAction(name);
    }

    @Nullable
    public static Action getAction(@NotNull String name) {
        return getActionService().getAction(name);
    }

    public static void execAction(@Nullable Player player, @NotNull String args) throws ActionException {
        getActionService().execAction(player, args);
    }

    public static void execAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        getActionService().execAction(player, args);
    }

    public static void execAction(@Nullable Player player, @NotNull String name, @NotNull String[] args) throws ActionException {
        getActionService().execAction(player, name, args);
    }

    public static void execAction(@NotNull ActionRequest request) throws ActionException {
        getActionService().execAction(request);
    }
}
