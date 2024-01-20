package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class ServerPlayConnectionJoinEventListener extends BaseEventListener implements ServerPlayConnectionEvents.Join {
    public ServerPlayConnectionJoinEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        super(playerBehaviorRecord);
    }

    @Override
    public void onPlayReady(ServerPlayNetworkHandler handler, PacketSender sender, MinecraftServer server) {
        this.playerBehaviorRecord.getBehaviorManager().playerJoin(handler.player);
    }
}
