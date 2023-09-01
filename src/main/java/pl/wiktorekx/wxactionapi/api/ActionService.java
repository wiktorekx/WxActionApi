package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public interface ActionService {
    void registerAction(Action action);

    void unregisterAction(String name);

    Action getAction(String name);

    void execAction(Player player, String args) throws ActionException;

    void execAction(Player player, String[] args) throws ActionException;

    void execAction(Player player, String name, String[] args) throws ActionException;

    void execAction(ActionRequest request) throws ActionException;
}
