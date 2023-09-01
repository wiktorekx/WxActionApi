package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

import java.io.*;
import java.util.Objects;

public class ConnectServerAction implements Action {
    private static final String BUNGEE_CORD_CHANNEL = "BungeeCord";
    private final Plugin plugin;

    public ConnectServerAction(Plugin plugin) {
        this.plugin = plugin;
        Bukkit.getMessenger().registerOutgoingPluginChannel(plugin, BUNGEE_CORD_CHANNEL);
    }

    @Override
    @NotNull
    public String getName() {
        return "connectserver";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        if(player == null) throw new ActionException("This action require player");
        if(args.length == 0) throw new ActionException("Require 1 argument: serverName");
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutput dataOutput = new DataOutputStream(byteArrayOutputStream);
            dataOutput.writeUTF("connect");
            dataOutput.writeUTF(args[0]);
            player.sendPluginMessage(plugin, BUNGEE_CORD_CHANNEL, byteArrayOutputStream.toByteArray());
        } catch (IOException e){
            throw new ActionException("", e);
        }
    }
}
