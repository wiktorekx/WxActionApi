package pl.wiktorekx.wxactionapi.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.WxActionApi;
import pl.wiktorekx.wxactionapi.plugin.placeholder.DefaultPlaceholderService;
import pl.wiktorekx.wxactionapi.plugin.placeholder.PlaceholderService;

public class WxActionPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        ActionService actionService = new ActionServiceImpl();
        WxActionApi.setActionService(actionService);
        PlaceholderService placeholderService = new DefaultPlaceholderService();
        DefaultActionRegistry.applyToActionService(this, actionService, placeholderService);
    }
}
