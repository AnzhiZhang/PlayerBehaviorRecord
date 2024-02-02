package com.zhanganzhi.playerbehaviorrecord.utils;

import net.minecraft.server.network.ServerPlayerEntity;

public class Utils {
    /**
     * Check if the player is a bot.
     *
     * @param serverPlayerEntity the player to check.
     * @return true if the player is a bot, false otherwise.
     */
    public static boolean isBot(ServerPlayerEntity serverPlayerEntity) {
        return serverPlayerEntity.networkHandler.getConnection().getAddress() == null;
    }
}
