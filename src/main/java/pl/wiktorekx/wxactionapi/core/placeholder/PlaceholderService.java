package pl.wiktorekx.wxactionapi.core.placeholder;

import org.bukkit.entity.Player;

public interface PlaceholderService {
    String replace(Player player, String input);
}
