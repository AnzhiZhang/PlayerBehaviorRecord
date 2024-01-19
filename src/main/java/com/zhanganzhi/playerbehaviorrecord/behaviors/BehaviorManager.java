package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.minecraft.server.MinecraftServer;

import com.zhanganzhi.playerbehaviorrecord.config.Config;
import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class BehaviorManager {
    private final Config config;
    private final ExecutorService executorService;

    public BehaviorManager(PlayerBehaviorRecord playerBehaviorRecord) {
        this.config = playerBehaviorRecord.getConfigManager().getConfig();
        this.executorService = Executors.newFixedThreadPool(
                this.config.getThreadPoolSize(),
                new ThreadFactoryBuilder().setNameFormat("player-behavior-record-%d").build()
        );
    }

    public void collectPlayerLocation(MinecraftServer server) {
        this.executorService.submit(new PlayerLocationBehavior(this.config, server));
    }
}
