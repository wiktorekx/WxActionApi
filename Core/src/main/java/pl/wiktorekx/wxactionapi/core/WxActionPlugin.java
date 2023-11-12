package pl.wiktorekx.wxactionapi.core;

import pl.wiktorekx.wxactionapi.core.placeholder.DefaultPlaceholderService;
import pl.wiktorekx.wxactionapi.core.placeholder.PlaceholderService;
import org.bukkit.plugin.java.JavaPlugin;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.WxActionApi;

public class WxActionPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        ActionService actionService = new ActionServiceImpl();
        WxActionApi.setActionService(actionService);
        PlaceholderService placeholderService = new DefaultPlaceholderService();
        DefaultActionRegistry.applyToActionService(this, actionService, placeholderService);
    }
}
