package com.zhanganzhi.playerbehaviorrecord.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;
import com.zhanganzhi.playerbehaviorrecord.events.listeners.*;

public class EventsManager {
    public EventsManager(PlayerBehaviorRecord playerBehaviorRecord) {
        ServerLifecycleEvents.SERVER_STOPPED.register(new ServerStoppedEventListener(playerBehaviorRecord));
        ServerTickEvents.END_SERVER_TICK.register(new EndTickEventListener(playerBehaviorRecord));
        ServerPlayConnectionEvents.JOIN.register(new ServerPlayConnectionJoinEventListener(playerBehaviorRecord));
        ServerPlayConnectionEvents.DISCONNECT.register(new ServerPlayConnectionDisconnectEventListener(playerBehaviorRecord));
    }
}
