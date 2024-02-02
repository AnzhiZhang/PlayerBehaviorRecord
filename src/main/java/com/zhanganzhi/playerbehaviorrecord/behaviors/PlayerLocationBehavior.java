package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.time.Instant;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;

import com.zhanganzhi.playerbehaviorrecord.config.Config;
import com.zhanganzhi.playerbehaviorrecord.kafka.KafkaManager;
import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;
import com.zhanganzhi.playerbehaviorrecord.utils.Utils;

public class PlayerLocationBehavior implements Runnable {
    private static final String KEY = "player_location";
    private final Config config;
    private final KafkaManager kafkaManager;
    private final MinecraftServer server;

    private record PlayerLocationData(
            Instant time,
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
        Instant now = Instant.now();
        String serverName = config.getServerName();

        // for each player
        for (ServerPlayerEntity serverPlayerEntity : server.getPlayerManager().getPlayerList()) {
            // only record real player
            if (Utils.isBot(serverPlayerEntity)) {
                continue;
            }

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
            kafkaManager.send(KEY, playerLocationData);
        }
    }
}
