package com.zhanganzhi.playerbehaviorrecord.kafka;

import java.util.Properties;

import lombok.Getter;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.zhanganzhi.playerbehaviorrecord.config.Config;
import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

@Getter
public class KafkaManager {
    private final KafkaProducer<String, String> producer;

    public KafkaManager(PlayerBehaviorRecord playerBehaviorRecord) {
        // config
        Config config = playerBehaviorRecord.getConfigManager().getConfig();

        // kafka config
        Properties kafkaConfig = new Properties();
        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getKafkaBootstrapServers());
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, config.getKafkaReconnectBackoffMaxMs());

        // create producer
        producer = new KafkaProducer<>(kafkaConfig);
    }

    public void onStopping() {
        producer.close();
    }
}
