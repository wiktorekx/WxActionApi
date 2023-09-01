package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public class WxActionApi {
    private static ActionService actionService;

    private WxActionApi() {
        throw new RuntimeException();
    }

    public static void setActionService(ActionService actionService) {
        if(WxActionApi.actionService != null) throw new RuntimeException();
        WxActionApi.actionService = actionService;
    }

    public static ActionService getActionService() {
        return actionService;
    }

    public static void registerAction(Action action) {
        getActionService().registerAction(action);
    }

    public static  void unregisterAction(String name) {
        getActionService().unregisterAction(name);
    }

    public static Action getAction(String name) {
        return getActionService().getAction(name);
    }

    public static void execAction(Player player, String args) throws ActionException {
        getActionService().execAction(player, args);
    }

    public static void execAction(Player player, String[] args) throws ActionException {
        getActionService().execAction(player, args);
    }

    public static void execAction(Player player, String name, String[] args) throws ActionException {
        getActionService().execAction(player, name, args);
    }

    public static void execAction(ActionRequest request) throws ActionException {
        getActionService().execAction(request);
    }
}
