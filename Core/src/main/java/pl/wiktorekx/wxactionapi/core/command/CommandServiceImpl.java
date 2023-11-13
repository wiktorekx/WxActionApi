package pl.wiktorekx.wxactionapi.core.command;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class CommandServiceImpl implements CommandService {
    private final Map<String, Command> registeredCommands = new HashMap<>();
    private final Map<String, Command> replacedCommands = new HashMap<>();
    private final Map<String, Command> knownCommands;
    private final String fallbackPrefix;

    private static Map<String, Command> getKnownCommands() {
        try {
            Server server = Bukkit.getServer();
            Object commandMap = server.getClass().getMethod("getCommandMap").invoke(server);
            Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
            knownCommandsField.setAccessible(true);
            return (Map<String, Command>) knownCommandsField.get(commandMap);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public CommandServiceImpl(String fallbackPrefix) {
        this.fallbackPrefix = fallbackPrefix.toLowerCase();
        this.knownCommands = getKnownCommands();
    }

    @Override
    public Collection<Command> getRegisteredCommands() {
        return Collections.unmodifiableCollection(registeredCommands.values());
    }

    @Override
    public void registerCommand(Command command){
        String name = command.getName().toLowerCase();
        if(registeredCommands.containsKey(name)) return;
        registeredCommands.put(name, command);
        knownCommands.put(fallbackPrefix + ":" + name, command);
        List<String> aliases = new ArrayList<>(command.getAliases());
        aliases.add(name);
        for(String alias : aliases) {
            Optional.ofNullable(knownCommands.put(alias, command))
                    .ifPresent(cmd -> replacedCommands.put(alias, cmd));
        }
        syncCommands();
    }

    @Override
    public void unregisterCommand(String name) {
        _unregisterCommand(name);
        syncCommands();
    }

    @Override
    public void unregisterRegisteredCommands() {
        if(registeredCommands.isEmpty()) return;
        for(String name : new ArrayList<>(registeredCommands.keySet())){
            _unregisterCommand(name);
        }
        syncCommands();
    }

    private void _unregisterCommand(String name) {
        Command command = registeredCommands.remove(name.toLowerCase());
        if(command != null) {
            name = command.getName();
            knownCommands.remove(fallbackPrefix + ":" + name);
            List<String> aliases = new ArrayList<>(command.getAliases());
            aliases.add(name);
            for(String alias : aliases) {
                knownCommands.remove(alias);
                Optional.ofNullable(replacedCommands.remove(alias))
                        .ifPresent(cmd -> knownCommands.put(alias, cmd));
            }
        }
    }

    private void syncCommands(){
        try {
            Server server = Bukkit.getServer();
            try {
                server.getClass().getMethod("syncCommands").invoke(server);
            } catch (NoSuchMethodException ignore) {}
        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
