package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public interface Action {
    @NotNull
    String getName();

    void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException;
}
