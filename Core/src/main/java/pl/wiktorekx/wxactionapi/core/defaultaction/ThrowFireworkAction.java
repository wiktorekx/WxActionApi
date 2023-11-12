package pl.wiktorekx.wxactionapi.core.defaultaction;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.wiktorekx.wxactionapi.api.Action;
import pl.wiktorekx.wxactionapi.api.exception.ActionException;

public class ThrowFireworkAction implements Action {
    @Override
    @NotNull
    public String getName() {
        return "throwfirework";
    }

    @Override
    public void onAction(@Nullable Player player, @NotNull String[] args) throws ActionException {
        if(player == null) throw new ActionException("This action require player");
        Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
        FireworkMeta fireworkMeta = firework.getFireworkMeta();
        fireworkMeta.addEffect(
                FireworkEffect.builder()
                        .withColor(Color.AQUA, Color.RED, Color.LIME, Color.WHITE, Color.ORANGE, Color.GREEN, Color.PURPLE)
                        .withFade(Color.AQUA, Color.RED, Color.LIME, Color.WHITE, Color.ORANGE, Color.GREEN, Color.PURPLE)
                        .with(FireworkEffect.Type.BALL)
                        .build()
        );
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);
    }
}
