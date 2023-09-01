package pl.wiktorekx.wxactionapi.plugin.defaultaction;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;
import pl.wiktorekx.wxactionapi.api.Action;

public class ThrowFireworkAction implements Action {
    @Override
    public String getName() {
        return "throwfirework";
    }

    @Override
    public void onAction(Player player, String[] args) {
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
