package pl.wiktorekx.wxactionapi.plugin;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.ActionRequest;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;
import pl.wiktorekx.wxactionapi.api.exception.NoFoundActionException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ActionServiceImpl implements ActionService {
    private final Map<String, Action> actionMap = new HashMap<>();

    @Override
    public void registerAction(@NotNull Action action){
        Objects.requireNonNull(action);
        Objects.requireNonNull(action.getName());
        actionMap.put(action.getName().toLowerCase().replace(" ", ""), action);
    }

    @Override
    public void unregisterAction(@NotNull String name){
        actionMap.remove(Objects.requireNonNull(name));
    }

    @Override
    @Nullable
    public Action getAction(@NotNull String name) {
        return actionMap.get(Objects.requireNonNull(name));
    }

    @Override
    public void execAction(@Nullable Player player, @NotNull String args) throws ActionException {
        execAction(ActionRequest.createActionRequest(player, Objects.requireNonNull(args)));
    }

    @Override
    public void execAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        execAction(ActionRequest.createActionRequest(player, Objects.requireNonNull(args)));
    }

    @Override
    public void execAction(@Nullable Player player, @NotNull String name, @NotNull String[] args) throws ActionException {
        execAction(ActionRequest.createActionRequest(player, Objects.requireNonNull(name), Objects.requireNonNull(args)));
    }

    @Override
    public void execAction(@NotNull ActionRequest request) throws ActionException {
        String name = request.getAction().toLowerCase().replace(" ", "");
        Action action = getAction(name);
        if (action == null) throw new NoFoundActionException(name);
        try {
            action.onAction(request.getPlayer(), request.getArgs());
        } catch (Throwable e) {
            throw new ActionException("Error in " + name + " action", e);
        }
    }
}
