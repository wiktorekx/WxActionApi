package pl.wiktorekx.wxactionapi.core;

import org.bukkit.configuration.ConfigurationSection;
import pl.wiktorekx.wxactionapi.core.command.CommandService;
import pl.wiktorekx.wxactionapi.core.command.CommandServiceImpl;
import pl.wiktorekx.wxactionapi.core.placeholder.DefaultPlaceholderService;
import pl.wiktorekx.wxactionapi.core.placeholder.PlaceholderService;
import org.bukkit.plugin.java.JavaPlugin;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.WxActionApi;

public class WxActionPlugin extends JavaPlugin {
    private CommandService commandService;

    @Override
    public void onLoad() {
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        ActionService actionService = new ActionServiceImpl();
        WxActionApi.setActionService(actionService);
        PlaceholderService placeholderService = new DefaultPlaceholderService();
        DefaultActionRegistry.applyToActionService(this, actionService, placeholderService);
        ConfigurationSection actionCommandSection = getConfig().getConfigurationSection("action-commands");
        commandService = new CommandServiceImpl(getName());
        if(actionCommandSection != null) {
            ActionCommandManager.loadActions(actionCommandSection)
                    .registerCommands(commandService);
        }
    }

    @Override
    public void onDisable() {
        commandService.unregisterRegisteredCommands();
    }
}
