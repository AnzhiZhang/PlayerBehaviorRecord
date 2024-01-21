package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class ServerStoppedEventListener extends BaseEventListener implements ServerLifecycleEvents.ServerStopped {
    public ServerStoppedEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        super(playerBehaviorRecord);
    }

    @Override
    public void onServerStopped(MinecraftServer server) {
        playerBehaviorRecord.onStopped();
    }
}
