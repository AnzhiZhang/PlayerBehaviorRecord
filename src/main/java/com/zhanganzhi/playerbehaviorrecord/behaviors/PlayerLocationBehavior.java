package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.time.LocalDateTime;

import com.alibaba.fastjson2.JSON;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import com.zhanganzhi.playerbehaviorrecord.config.Config;
import com.zhanganzhi.playerbehaviorrecord.kafka.KafkaManager;
import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class PlayerLocationBehavior implements Runnable {
    private static final String KEY = "player_location";
    private final Config config;
    private final KafkaManager kafkaManager;
    private final MinecraftServer server;

    private record PlayerLocationData(
            LocalDateTime time,
            String serverName,
            String playerUUID,
            String playerName,
            String worldName,
            Double x,
            Double y,
            Double z
    ) {
    }

    public PlayerLocationBehavior(PlayerBehaviorRecord playerBehaviorRecord, MinecraftServer server) {
        this.config = playerBehaviorRecord.getConfigManager().getConfig();
        this.kafkaManager = playerBehaviorRecord.getKafkaManager();
        this.server = server;
    }

    @Override
    public void run() {
        // data
        LocalDateTime now = LocalDateTime.now();
        String serverName = config.getServerName();

        // for each player
        for (ServerPlayerEntity serverPlayerEntity : server.getPlayerManager().getPlayerList()) {
            // get is bot
            boolean isBot = serverPlayerEntity.networkHandler.connection.getAddress() == null;

            // only record player
            if (!isBot) {
                // get data and create object
                Vec3d pos = serverPlayerEntity.getPos();
                PlayerLocationData playerLocationData = new PlayerLocationData(
                        now,
                        serverName,
                        serverPlayerEntity.getUuidAsString(),
                        serverPlayerEntity.getEntityName(),
                        serverPlayerEntity.getServerWorld().getRegistryKey().getValue().toString(),
                        pos.x,
                        pos.y,
                        pos.z
                );

                // send
                kafkaManager.send(KEY, JSON.toJSONString(playerLocationData));
            }
        }
    }
}
