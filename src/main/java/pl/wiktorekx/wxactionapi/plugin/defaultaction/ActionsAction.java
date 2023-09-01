package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.ActionService;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

import java.util.ArrayList;
import java.util.List;

public class ActionsAction implements Action {
    private final ActionService actionService;

    public ActionsAction(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    @NotNull
    public String getName() {
        return "actions";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        List<String> currentAction = new ArrayList<>();
        for(int i = 0; i < args.length; i++) {
            String line = args[i];
            String findLine = line.replace(" ", "");
            if(findLine.isEmpty()) continue;
            if(findLine.equals(";")) {
                execAction(player, currentAction, i);
                currentAction.clear();
                continue;
            }
            if(line.equals("\\;"))
                line = line.substring(1);
            currentAction.add(line);
        }
        execAction(player, currentAction, args.length);
    }

    private void execAction(Player player, List<String> action, int n) throws ActionException {
        if(!action.isEmpty()) {
            try {
                actionService.execAction(player, action.toArray(new String[0]));
            } catch (ActionException actionException){
                throw new ActionException("Error in " + n, actionException);
            }
        }
    }
}
