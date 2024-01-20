package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class ServerPlayConnectionDisconnectEventListener extends BaseEventListener implements ServerPlayConnectionEvents.Disconnect {
    public ServerPlayConnectionDisconnectEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        super(playerBehaviorRecord);
    }

    @Override
    public void onPlayDisconnect(ServerPlayNetworkHandler handler, MinecraftServer server) {
        this.playerBehaviorRecord.getBehaviorManager().playerDisconnect(handler.player);
    }
}
