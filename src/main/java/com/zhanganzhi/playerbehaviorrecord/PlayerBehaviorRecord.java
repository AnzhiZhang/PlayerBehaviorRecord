package com.zhanganzhi.playerbehaviorrecord;

import lombok.Getter;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.zhanganzhi.playerbehaviorrecord.config.ConfigManager;
import com.zhanganzhi.playerbehaviorrecord.behaviors.BehaviorManager;
import com.zhanganzhi.playerbehaviorrecord.events.EventsManager;
import com.zhanganzhi.playerbehaviorrecord.kafka.KafkaManager;

@Getter
public class PlayerBehaviorRecord implements ModInitializer {
    public static final Logger log = LogManager.getLogger("playerbehaviorrecord");
    private ConfigManager configManager;
    private BehaviorManager behaviorManager;
    private EventsManager eventsManager;
    private KafkaManager kafkaManager;

    @Override
    public void onInitialize() {
        configManager = new ConfigManager();
        behaviorManager = new BehaviorManager(this);
        eventsManager = new EventsManager(this);
        kafkaManager = new KafkaManager(this);
    }

    public void onStopping() {
        kafkaManager.onStopping();
    }
}
