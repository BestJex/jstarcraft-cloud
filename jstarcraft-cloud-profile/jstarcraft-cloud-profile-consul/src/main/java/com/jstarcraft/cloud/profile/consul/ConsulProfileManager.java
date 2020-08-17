package com.jstarcraft.cloud.profile.consul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetValue;
import com.jstarcraft.cloud.profile.ProfileManager;
import com.jstarcraft.cloud.profile.ProfileMonitor;
import com.jstarcraft.core.common.configuration.Configurator;
import com.jstarcraft.core.common.configuration.JsonConfigurator;
import com.jstarcraft.core.common.configuration.PropertyConfigurator;
import com.jstarcraft.core.common.configuration.XmlConfigurator;
import com.jstarcraft.core.common.configuration.YamlConfigurator;

/**
 * Consul配置管理器
 * 
 * @author Birdy
 *
 */
public class ConsulProfileManager implements ProfileManager {

    private static final Logger logger = LoggerFactory.getLogger(ConsulProfileManager.class);

    private ConsulClient consul;

    private String format;

    public ConsulProfileManager(ConsulClient consul, String format) {
        this.consul = consul;
        this.format = format;
    }

    @Override
    public Configurator getConfiguration(String name) {
        Response<GetValue> response = consul.getKVValue(name, QueryParams.DEFAULT);
        GetValue keyValue = response.getValue();
        String value = keyValue.getDecodedValue();
        switch (format) {
        case "json":
            return new JsonConfigurator(value);
        case "properties":
            return new PropertyConfigurator(value);
        case "xml":
            return new XmlConfigurator(value);
        case "yaml":
            return new YamlConfigurator(value);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void registerMonitor(String name, ProfileMonitor monitor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unregisterMonitor(String name, ProfileMonitor monitor) {
        // TODO Auto-generated method stub

    }

}
