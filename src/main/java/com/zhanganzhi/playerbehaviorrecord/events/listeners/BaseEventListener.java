package com.zhanganzhi.playerbehaviorrecord.events.listeners;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class BaseEventListener {
    protected final PlayerBehaviorRecord playerBehaviorRecord;

    public BaseEventListener(PlayerBehaviorRecord playerBehaviorRecord) {
        this.playerBehaviorRecord = playerBehaviorRecord;
    }
}
