package com.zhanganzhi.playerbehaviorrecord.behaviors;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class BehaviorManager {
    private final PlayerBehaviorRecord playerBehaviorRecord;
    private final ExecutorService executorService;
    private final PlayerActivityBehaviorManager playerActivityBehaviorManager;

    public BehaviorManager(PlayerBehaviorRecord playerBehaviorRecord) {
        // base members
        this.playerBehaviorRecord = playerBehaviorRecord;
        this.executorService = Executors.newFixedThreadPool(
                this.playerBehaviorRecord.getConfigManager().getConfig().getThreadPoolSize(),
                new ThreadFactoryBuilder().setNameFormat("player-behavior-record-%d").build()
        );

        // behaviors
        this.playerActivityBehaviorManager = new PlayerActivityBehaviorManager(this.playerBehaviorRecord);
    }

    public void onStopping() {
        this.executorService.shutdown();
    }

    public void submitRunnable(Runnable runnable) {
        this.executorService.submit(runnable);
    }

    public void collectPlayerLocation(MinecraftServer server) {
        this.submitRunnable(new PlayerLocationBehavior(this.playerBehaviorRecord, server));
    }

    public void playerJoin(ServerPlayerEntity player) {
        this.playerActivityBehaviorManager.join(player);
    }

    public void playerDisconnect(ServerPlayerEntity player) {
        this.playerActivityBehaviorManager.disconnect(player);
    }
}
