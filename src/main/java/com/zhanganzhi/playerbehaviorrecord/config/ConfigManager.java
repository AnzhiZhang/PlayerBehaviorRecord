package com.zhanganzhi.playerbehaviorrecord.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.Logger;

import com.zhanganzhi.playerbehaviorrecord.PlayerBehaviorRecord;

public class ConfigManager {
    private static final Logger log = PlayerBehaviorRecord.log;
    private final Path configDir = FabricLoader.getInstance().getConfigDir();
    private final File configFile = new File(configDir.toFile(), "PlayerBehaviorRecord.json");

    @Getter
    private final Config config;

    public ConfigManager() {
        // local variables
        Config configTemp;
        boolean saveFlag = false;

        // create config dir if not exists
        if (!configDir.toFile().exists()) {
            configDir.toFile().mkdirs();
        }

        // create or load config file
        if (!configFile.exists()) {
            // create config file if not exists
            try {
                configTemp = new Config();
                saveFlag = true;
            } catch (Exception e) {
                log.error("An error occurred during create config file.", e);
                throw new RuntimeException(e);
            }
        } else {
            // load config file
            JSONObject configJSONObject;
            try {
                String configString = Files.readString(configFile.toPath());
                configJSONObject = JSON.parseObject(configString);
                configTemp = configJSONObject.toJavaObject(Config.class);
            } catch (IOException e) {
                log.error("An error occurred during load config file.", e);
                throw new RuntimeException(e);
            }

            // check config file
            for (Field field : Config.class.getDeclaredFields()) {
                if (!configJSONObject.containsKey(field.getName())) {
                    log.warn("Config field \"{}\" is missing, use default value.", field.getName());
                    saveFlag = true;
                }
            }
        }

        // set config
        this.config = configTemp;

        // save config file if needed
        if (saveFlag) {
            save();
        }
    }

    private void save() {
        try {
            FileWriter fileWriter = new FileWriter(configFile);
            fileWriter.write(JSON.toJSONString(
                    config,
                    JSONWriter.Feature.PrettyFormat
            ));
            fileWriter.close();
        } catch (IOException e) {
            log.error("An error occurred during save config file.", e);
        }
    }
}
