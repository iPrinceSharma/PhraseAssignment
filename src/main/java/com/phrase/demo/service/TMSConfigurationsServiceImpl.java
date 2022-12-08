package com.phrase.demo.service;

import com.phrase.demo.cutomInterfaces.TMSConfigurationInterface;
import com.phrase.demo.dto.ProjectRepository;
import com.phrase.demo.dto.request.TMSConfiguration;
import com.phrase.demo.dto.response.LoginInfo;
import com.phrase.demo.utility.NetworkUtility;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;

@Service
@AllArgsConstructor
public class TMSConfigurationsServiceImpl implements TMSConfigurationInterface {

    private ProjectRepository projectRepository;

    @Override
    public TMSConfiguration getConfiguration() {
        TMSConfiguration configuration = new TMSConfiguration();
        Iterable<TMSConfiguration> configuration1= projectRepository.findAll();
        Iterator<TMSConfiguration> iterator = configuration1.iterator();
        if(iterator.hasNext()){
            // if entry found in the database then update the object
            configuration = iterator.next();
        }
        return configuration;
    }

    @Override
    public void saveConfiguration(TMSConfiguration configuration) {
        projectRepository.save(configuration);
    }
}
