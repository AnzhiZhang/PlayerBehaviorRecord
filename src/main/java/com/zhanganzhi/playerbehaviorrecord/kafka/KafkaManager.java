package com.zhanganzhi.playerbehaviorrecord.kafka;

import java.time.Duration;
import java.util.Properties;

import com.alibaba.fastjson2.JSON;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.Logger;

import com.zhanganzhi.playerbehaviorrecord.config.Config;
import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class KafkaManager {
    private static final Logger log = PlayerBehaviorRecord.log;
    private final String topic;
    private final KafkaProducer<String, String> producer;

    public KafkaManager(PlayerBehaviorRecord playerBehaviorRecord) {
        // config
        Config config = playerBehaviorRecord.getConfigManager().getConfig();
        this.topic = config.getKafkaTopic();

        // kafka config
        Properties kafkaConfig = new Properties();
        kafkaConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getKafkaBootstrapServers());
        kafkaConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaConfig.put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, config.getKafkaReconnectBackoffMaxMs());

        // create producer
        this.producer = new KafkaProducer<>(kafkaConfig);
    }

    public void onStopped() {
        producer.close(Duration.ofSeconds(5));
    }

    public void send(String key, Record value) {
        send(key, JSON.toJSONString(value));
    }

    public void send(String key, String value) {
        producer.send(
                new ProducerRecord<>(topic, key, value),
                (metadata, exception) -> {
                    if (exception != null) {
                        log.error("Send record to kafka failed: {}", exception.getMessage(), exception);
                    }
                }
        );
    }
}
