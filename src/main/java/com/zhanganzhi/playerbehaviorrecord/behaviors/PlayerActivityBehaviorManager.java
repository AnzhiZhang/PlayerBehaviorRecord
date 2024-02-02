package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import lombok.Builder;
import lombok.Data;
import net.minecraft.server.network.ServerPlayerEntity;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;
import com.zhanganzhi.playerbehaviorrecord.utils.Utils;

public class PlayerActivityBehaviorManager {
    private static final String KEY = "player_activity";
    private final PlayerBehaviorRecord playerBehaviorRecord;
    private final HashMap<String, PlayerActivityData> playerActivityDataHashMap = new HashMap<>();

    @Data
    @Builder
    private static class PlayerActivityData {
        private Instant time;
        private String serverName;
        private String loginIP;
        private String playerUUID;
        private String playerName;
        private Instant loginAt;
        private Instant logoutAt;
        private Long onlineTimeSeconds;
    }

    public PlayerActivityBehaviorManager(PlayerBehaviorRecord playerBehaviorRecord) {
        this.playerBehaviorRecord = playerBehaviorRecord;
    }

    public void join(ServerPlayerEntity player) {
        // only record real player
        if (Utils.isBot(player)) {
            return;
        }

        // save data to variable
        String playerUUID = player.getUuidAsString();
        InetSocketAddress address = (InetSocketAddress) player.networkHandler.connection.getAddress();

        // save data to map
        this.playerActivityDataHashMap.put(
                playerUUID,
                PlayerActivityData
                        .builder()
                        .serverName(this.playerBehaviorRecord.getConfigManager().getConfig().getServerName())
                        .loginIP(address.getAddress().getHostAddress())
                        .playerUUID(playerUUID)
                        .playerName(player.getEntityName())
                        .loginAt(Instant.now())
                        .build()
        );
    }

    public void disconnect(ServerPlayerEntity player) {
        // only record real player
        if (Utils.isBot(player)) {
            return;
        }

        // save data to variable
        String playerUUID = player.getUuidAsString();
        PlayerActivityData playerActivityData = this.playerActivityDataHashMap.get(playerUUID);
        Instant loginAt = playerActivityData.getLoginAt();
        Instant now = Instant.now();

        // set data
        playerActivityData.setTime(now);
        playerActivityData.setLogoutAt(now);
        playerActivityData.setOnlineTimeSeconds(Duration.between(loginAt, now).getSeconds());

        // clean map
        this.playerActivityDataHashMap.remove(playerUUID);

        // send data to kafka
        this.playerBehaviorRecord.getKafkaManager().send(KEY, playerActivityData);
    }
}
