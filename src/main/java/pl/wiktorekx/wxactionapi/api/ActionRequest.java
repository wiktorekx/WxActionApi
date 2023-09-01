package pl.wiktorekx.wxactionapi.api;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Objects;

public class ActionRequest {
    private final String action;
    private final String[] args;
    private Player player;

    public static ActionRequest createActionRequest(String request) {
        if(request.isEmpty()) throw new IllegalArgumentException("Request is empty");
        return createActionRequest(Arrays.stream(request.split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new));
    }

    public static ActionRequest createActionRequest(String[] request) {
        if(request.length == 0) throw new IllegalArgumentException("Request too short");
        return new ActionRequest(null, request[0], Arrays.copyOfRange(request, 1, request.length));
    }

    public static ActionRequest createActionRequest(String action, String[] request) {
        return new ActionRequest(null, action, request);
    }

    public static ActionRequest createActionRequest(Player player, String request) {
        if(request.isEmpty()) throw new IllegalArgumentException("Request is empty");
        return createActionRequest(player, Arrays.stream(request.split(" ")).filter(s -> !s.isEmpty()).toArray(String[]::new));
    }

    public static ActionRequest createActionRequest(Player player, String[] request) {
        if(request.length == 0) throw new IllegalArgumentException("Request too short");
        return new ActionRequest(player, request[0], Arrays.copyOfRange(request, 1, request.length));
    }

    public static ActionRequest createActionRequest(Player player, String action, String[] args) {
        return new ActionRequest(player, action, args);
    }

    private ActionRequest(Player player, String action, String[] args) {
        this.player = player;
        this.action = Objects.requireNonNull(action);
        this.args = Objects.requireNonNull(args);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getAction() {
        return action;
    }

    public String[] getArgs() {
        return args;
    }
}
