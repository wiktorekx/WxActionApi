package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Objects;

public class ActionRequest {
    @NotNull
    private final String action;
    @NotNull
    private final String[] args;
    @Nullable
    private Player player;

    public static ActionRequest createActionRequest(@NotNull String request) {
        if(Objects.requireNonNull(request, "request is null").isEmpty()) throw new IllegalArgumentException("Request is empty");
        return createActionRequest(
                Arrays.stream(request.split(" "))
                        .filter(s -> !s.isEmpty()).toArray(String[]::new)
        );
    }

    public static ActionRequest createActionRequest(@NotNull String[] request) {
        if(Objects.requireNonNull(request, "request is null").length == 0) throw new IllegalArgumentException("Request too short");
        return new ActionRequest(null,
                request[0],
                Arrays.copyOfRange(request, 1, request.length)
        );
    }

    public static ActionRequest createActionRequest(@NotNull String action, @NotNull String[] args) {
        return new ActionRequest(null,
                Objects.requireNonNull(action, "action is null"),
                Objects.requireNonNull(args, "args is null")
        );
    }

    public static ActionRequest createActionRequest(@Nullable Player player, @NotNull String request) {
        if(Objects.requireNonNull(request, "request is null").isEmpty()) throw new IllegalArgumentException("Request is empty");
        return createActionRequest(player,
                Arrays.stream(request.split(" "))
                        .filter(s -> !s.isEmpty()).toArray(String[]::new)
        );
    }

    public static ActionRequest createActionRequest(@Nullable Player player, @NotNull String[] request) {
        if(Objects.requireNonNull(request, "request is null").length == 0) throw new IllegalArgumentException("Request too short");
        return new ActionRequest(player,
                request[0],
                Arrays.copyOfRange(request, 1, request.length)
        );
    }

    public static ActionRequest createActionRequest(@Nullable Player player, @NotNull String action, @NotNull String[] args) {
        return new ActionRequest(player,
                Objects.requireNonNull(action, "action is null"),
                Objects.requireNonNull(args, "args is null")
        );
    }

    private ActionRequest(@Nullable Player player, @NotNull String action, @NotNull String[] args) {
        this.player = player;
        this.action = action;
        this.args = args;
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }


    public void setPlayer(@Nullable Player player) {
        this.player = player;
    }

    @NotNull
    public String getAction() {
        return action;
    }

    @NotNull
    public String[] getArgs() {
        return args;
    }
}
