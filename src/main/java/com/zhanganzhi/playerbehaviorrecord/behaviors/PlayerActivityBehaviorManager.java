package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

import net.minecraft.server.network.ServerPlayerEntity;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class PlayerActivityBehaviorManager {
    private static final String KEY = "player_activity";
    private final PlayerBehaviorRecord playerBehaviorRecord;
    private final HashMap<String, LocalDateTime> playerJoinTime = new HashMap<>();

    private record PlayerActivityData(
            LocalDateTime time,
            String serverName,
            String playerUUID,
            String playerName,
            LocalDateTime loginAt,
            LocalDateTime logoutAt,
            Long onlineTimeSeconds
    ) {
    }

    public PlayerActivityBehaviorManager(PlayerBehaviorRecord playerBehaviorRecord) {
        this.playerBehaviorRecord = playerBehaviorRecord;
    }

    public void join(ServerPlayerEntity player) {
        this.playerJoinTime.put(player.getUuidAsString(), LocalDateTime.now());
    }

    public void disconnect(ServerPlayerEntity player) {
        // save data to variable
        String playerUUID = player.getUuidAsString();
        LocalDateTime loginAt = this.playerJoinTime.get(playerUUID);
        LocalDateTime logoutAt = LocalDateTime.now();

        // clean map
        this.playerJoinTime.remove(playerUUID);

        // player activity data
        PlayerActivityData playerActivityData = new PlayerActivityData(
                logoutAt,
                this.playerBehaviorRecord.getConfigManager().getConfig().getServerName(),
                playerUUID,
                player.getEntityName(),
                loginAt,
                logoutAt,
                Duration.between(loginAt, logoutAt).toSeconds()
        );

        // send data to kafka
        this.playerBehaviorRecord.getKafkaManager().send(KEY, playerActivityData);
    }
}
