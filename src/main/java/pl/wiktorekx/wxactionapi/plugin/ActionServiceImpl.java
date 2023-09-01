package pl.wiktorekx.wxactionapi.plugin;

import org.bukkit.entity.Player;
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
    public void registerAction(Action action){
        actionMap.put(action.getName().toLowerCase().replace(" ", ""), action);
    }

    @Override
    public void unregisterAction(String name){
        actionMap.remove(name);
    }

    @Override
    public Action getAction(String name) {
        return actionMap.get(name);
    }

    @Override
    public void execAction(Player player, String args) throws ActionException {
        Objects.requireNonNull(player);
        execAction(ActionRequest.createActionRequest(player, args));
    }

    @Override
    public void execAction(Player player, String[] args) throws ActionException {
        Objects.requireNonNull(player);
        execAction(ActionRequest.createActionRequest(player, args));
    }

    @Override
    public void execAction(Player player, String name, String[] args) throws ActionException {
        Objects.requireNonNull(player);
        execAction(ActionRequest.createActionRequest(player, name, args));
    }

    @Override
    public void execAction(ActionRequest request) throws ActionException {
        String name = request.getAction().toLowerCase().replace(" ", "");
        Player player = Objects.requireNonNull(request.getPlayer());
        Action action = getAction(name);
        if (action == null) throw new NoFoundActionException(name);
        action.onAction(player, request.getArgs());
    }
}
