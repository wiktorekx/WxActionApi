package pl.wiktorekx.wxactionapi.plugin;

import org.bukkit.plugin.java.JavaPlugin;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.WxActionApiProvider;
import pl.wiktorekx.wxactionapi.plugin.placeholder.DefaultPlaceholderService;
import pl.wiktorekx.wxactionapi.plugin.placeholder.PlaceholderService;

public class WxActionPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        ActionService actionService = new ActionServiceImpl();
        WxActionApiProvider.setActionService(actionService);
        PlaceholderService placeholderService = new DefaultPlaceholderService();
        DefaultActionRegistry.applyToActionService(this, actionService, placeholderService);
    }
}
