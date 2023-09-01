package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public interface Action {
    String getName();

    void onAction(Player player, String[] args) throws ActionException;
}
