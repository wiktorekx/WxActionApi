package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public class PlaySoundAction implements Action {
    @Override
    @NotNull
    public String getName() {
        return "playsound";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        try {
            Sound sound = Sound.valueOf(args[0].toUpperCase());
            float volume = 1;
            float pitch = 1;
            if(args.length > 1) volume = Float.parseFloat(args[1]);
            if(args.length > 2) pitch = Float.parseFloat(args[2]);
            player.playSound(player.getLocation(), sound, volume, pitch);
        } catch (Exception e){
            throw new ActionException("", e);
        }
    }
}
