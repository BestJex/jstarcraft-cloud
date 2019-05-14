package com.jstarcraft.cloud.configuration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mock")
public class ConfigProperties {

    private String config;

    public String getConfig() {
        return config;
    }

}