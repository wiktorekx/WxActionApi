package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public interface ActionService {
    void registerAction(@NotNull Action action);

    void unregisterAction(@NotNull String name);

    @Nullable
    Action getAction(@NotNull String name);

    void execAction(@Nullable Player player, @NotNull String args) throws ActionException;

    void execAction(@Nullable Player player, @NotNull String[] args) throws ActionException;

    void execAction(@Nullable Player player, @NotNull String name, @NotNull String[] args) throws ActionException;

    void execAction(@NotNull ActionRequest request) throws ActionException;
}
