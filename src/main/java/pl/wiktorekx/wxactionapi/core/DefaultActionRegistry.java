package pl.wiktorekx.wxactionapi.core;

import org.bukkit.plugin.Plugin;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.core.defaultaction.*;
import pl.wiktorekx.wxactionapi.core.placeholder.PlaceholderService;

public class DefaultActionRegistry {

    private DefaultActionRegistry() {
        throw new RuntimeException();
    }

    public static void applyToActionService(Plugin plugin, ActionService actionService, PlaceholderService placeholderService) {
        actionService.registerAction(new ConnectServerAction(plugin));
        actionService.registerAction(new ActionsAction(actionService));
        actionService.registerAction(new PermissionAction(actionService));
        actionService.registerAction(new ForOnlinePlayersAction(actionService));
        actionService.registerAction(new SendMessageAction(placeholderService));
        actionService.registerAction(new SendCommandAction(placeholderService));
        actionService.registerAction(new ThrowFireworkAction());
        actionService.registerAction(new PlaySoundAction());
    }
}
