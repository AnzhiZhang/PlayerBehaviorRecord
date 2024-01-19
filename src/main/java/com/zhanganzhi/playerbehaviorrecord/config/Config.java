package com.zhanganzhi.playerbehaviorrecord.config;

import lombok.Data;

@Data
public class Config {
    // plugin config
    private int threadPoolSize = 10;

    // events config
    private String serverName = "server";
    private int playerLocationDataPointIntervalMs = 500;

    // kafka config
    private String kafkaBootstrapServers = "localhost:9092";
    private String kafkaTopic = "minecraft";
}
