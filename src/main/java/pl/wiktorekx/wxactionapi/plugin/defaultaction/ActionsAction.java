package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.entity.Player;
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
    public String getName() {
        return "actions";
    }

    @Override
    public void onAction(Player player, String[] args) throws ActionException {
        List<String> currentAction = new ArrayList<>();
        for(String line : args) {
            String findLine = line.replace(" ", "");
            if(findLine.isEmpty()) continue;
            if(findLine.equals(";")) {
                execAction(player, currentAction);
                currentAction.clear();
                continue;
            }
            if(line.equals("\\;"))
                line = line.substring(1);
            currentAction.add(line);
        }
        execAction(player, currentAction);
    }

    private void execAction(Player player, List<String> action) throws ActionException {
        if(!action.isEmpty())
            actionService.execAction(player, action.toArray(new String[0]));
    }
}
