package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class ServerStoppingEventListener extends BaseEventListener implements ServerLifecycleEvents.ServerStopping {
    public ServerStoppingEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        super(playerBehaviorRecord);
    }

    @Override
    public void onServerStopping(MinecraftServer server) {
        playerBehaviorRecord.onStopping();
    }
}
