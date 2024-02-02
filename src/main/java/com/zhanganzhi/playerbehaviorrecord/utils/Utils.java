package com.zhanganzhi.playerbehaviorrecord.utils;

import net.minecraft.server.network.ServerPlayerEntity;

public class Utils {
    public static boolean isBot(ServerPlayerEntity serverPlayerEntity) {
        return serverPlayerEntity.networkHandler.getConnection().getAddress() == null;
    }
}
