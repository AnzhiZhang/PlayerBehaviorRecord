package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class EndTickEventListener extends BaseEventListener implements ServerTickEvents.EndTick {
    private final int periodMillis;
    private long lastTickTime = 0;
    private long lastTickOffset = 0;

    public EndTickEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        super(playerBehaviorRecord);
        this.periodMillis = playerBehaviorRecord.getConfigManager().getConfig().getDataPointIntervalMs();
    }

    @Override
    public void onEndTick(MinecraftServer server) {
        // get system time
        long currentTime = System.currentTimeMillis();

        // first tick
        if (lastTickTime == 0) {
            lastTickTime = currentTime;
            return;
        }

        // calculate time difference
        long timeDifferenceWithOffset = (currentTime - lastTickTime) + lastTickOffset;

        // check one period has passed
        if (timeDifferenceWithOffset >= periodMillis) {
            // update last tick time and offset
            lastTickTime = currentTime;
            lastTickOffset = timeDifferenceWithOffset - periodMillis;

            // collect player location
            playerBehaviorRecord.getBehaviorManager().collectPlayerLocation(server);
        }
    }
}
