package pl.wiktorekx.wxactionapi.core;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.wiktorekx.wxactionapi.api.WxActionApi;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;
import pl.wiktorekx.wxactionapi.core.command.CommandService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ActionCommandManager {
    public final Map<String, String> actionCommandMap;

    public static ActionCommandManager loadActions(ConfigurationSection section) {
        Objects.requireNonNull(section, "section is null");
        Map<String, String> actionCommandMap = new HashMap<>();
        for(String key : section.getKeys(false)){
            String action = section.getString(key);
            Objects.requireNonNull(action, "Can`t load action from " + key);
            actionCommandMap.put(key, action);
        }
        return new ActionCommandManager(actionCommandMap);
    }

    public ActionCommandManager(Map<String, String> actionCommandMap) {
        this.actionCommandMap = new HashMap<>(actionCommandMap);
    }

    public void registerCommands(CommandService commandService) {
        for(Map.Entry<String, String> entry : entrySet()){
            commandService.registerCommand(new Command(entry.getKey()) {
                @Override
                public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
                    if(sender instanceof Player) {
                        try {
                            WxActionApi.execAction((Player) sender, entry.getValue());
                        } catch (ActionException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    return true;
                }
            });
        }
    }

    public Set<Map.Entry<String, String>> entrySet() {
        return actionCommandMap.entrySet();
    }
}
