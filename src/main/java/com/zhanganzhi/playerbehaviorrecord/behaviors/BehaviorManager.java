package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.minecraft.server.MinecraftServer;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class BehaviorManager {
    private final PlayerBehaviorRecord playerBehaviorRecord;
    private final ExecutorService executorService;

    public BehaviorManager(PlayerBehaviorRecord playerBehaviorRecord) {
        this.playerBehaviorRecord = playerBehaviorRecord;
        this.executorService = Executors.newFixedThreadPool(
                this.playerBehaviorRecord.getConfigManager().getConfig().getThreadPoolSize(),
                new ThreadFactoryBuilder().setNameFormat("player-behavior-record-%d").build()
        );
    }

    public void collectPlayerLocation(MinecraftServer server) {
        this.executorService.submit(new PlayerLocationBehavior(this.playerBehaviorRecord, server));
    }
}
