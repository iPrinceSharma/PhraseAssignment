package com.phrase.demo.cutomInterfaces;

import com.phrase.demo.dto.request.TMSConfiguration;

public interface TMSConfigurationInterface {
    TMSConfiguration getConfiguration();
    void saveConfiguration(TMSConfiguration configuration);
}
