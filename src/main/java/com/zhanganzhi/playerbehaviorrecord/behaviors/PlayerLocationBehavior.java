package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.time.LocalDateTime;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import com.zhanganzhi.playerbehaviorrecord.config.Config;

public class PlayerLocationBehavior implements Runnable {
    private final Config config;
    private final MinecraftServer server;

    public PlayerLocationBehavior(Config config, MinecraftServer server) {
        this.config = config;
        this.server = server;
    }

    @Override
    public void run() {
        LocalDateTime now = LocalDateTime.now();
        String serverName = config.getServerName();

        for (ServerPlayerEntity serverPlayerEntity : server.getPlayerManager().getPlayerList()) {
            boolean isBot = serverPlayerEntity.networkHandler.connection.getAddress() == null;
            System.out.println("========================================");
            System.out.println(now + " " + serverName);
            System.out.println(serverPlayerEntity.getUuidAsString());
            System.out.println(serverPlayerEntity.getEntityName());
            System.out.println("Is Bot: " + isBot);
            System.out.println(serverPlayerEntity.getServerWorld().getRegistryKey().getValue().toString());
            System.out.println(serverPlayerEntity.getPos().toString());
            System.out.println("========================================");
        }
    }
}
