package pl.wiktorekx.wxactionapi.core.command;

import org.bukkit.command.Command;

import java.util.Collection;

public interface CommandService {
    Collection<Command> getRegisteredCommands();

    void registerCommand(Command command);

    void unregisterCommand(String name);

    void unregisterRegisteredCommands();
}
