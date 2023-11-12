package pl.wiktorekx.wxactionapi.core.utils;

import org.bukkit.ChatColor;

public class ChatUtils {
    private ChatUtils() {
        throw new RuntimeException();
    }

    public static String color(String input) {
        if(input == null || input.isEmpty()) return input;
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
