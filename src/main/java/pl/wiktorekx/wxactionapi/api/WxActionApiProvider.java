package pl.wiktorekx.wxactionapi.api;

public class WxActionApiProvider {
    private static ActionService actionService;

    private WxActionApiProvider() {
        throw new RuntimeException();
    }

    public static void setActionService(ActionService actionService) {
        if(WxActionApiProvider.actionService != null) throw new RuntimeException();
        WxActionApiProvider.actionService = actionService;
    }

    public static ActionService getActionService() {
        return actionService;
    }
}
