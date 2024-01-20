package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class ServerStoppingEventListener implements ServerLifecycleEvents.ServerStopping {
    private final PlayerBehaviorRecord playerBehaviorRecord;

    public ServerStoppingEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        this.playerBehaviorRecord = playerBehaviorRecord;
    }

    @Override
    public void onServerStopping(MinecraftServer server) {
        playerBehaviorRecord.onStopping();
    }
}
