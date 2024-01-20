package com.zhanganzhi.playerbehaviorrecord.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;
import com.zhanganzhi.playerbehaviorrecord.events.listeners.EndTickEventListener;
import com.zhanganzhi.playerbehaviorrecord.events.listeners.ServerStoppingEventListener;

public class EventsManager {
    public EventsManager(PlayerBehaviorRecord playerBehaviorRecord) {
        ServerLifecycleEvents.SERVER_STOPPING.register(new ServerStoppingEventListener(playerBehaviorRecord));
        ServerTickEvents.END_SERVER_TICK.register(new EndTickEventListener(
                playerBehaviorRecord,
                playerBehaviorRecord.getConfigManager().getConfig().getPlayerLocationDataPointIntervalMs()
        ));
    }
}
