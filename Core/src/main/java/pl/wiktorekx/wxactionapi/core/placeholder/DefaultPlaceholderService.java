package pl.wiktorekx.wxactionapi.core.placeholder;

import org.bukkit.entity.Player;

public class DefaultPlaceholderService implements PlaceholderService {

    @Override
    public String replace(Player player, String input) {
        return input.replace("%player-name%", player.getName());
    }
}
