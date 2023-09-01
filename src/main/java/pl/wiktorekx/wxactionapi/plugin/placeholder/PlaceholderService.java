package pl.wiktorekx.wxactionapi.plugin.placeholder;

import org.bukkit.entity.Player;

public interface PlaceholderService {
    String replace(Player player, String input);
}
