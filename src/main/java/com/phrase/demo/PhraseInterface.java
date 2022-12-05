package com.phrase.demo;

import com.phrase.demo.DTO.Configuration;
import com.phrase.demo.DTO.Project;

import java.util.List;

public interface PhraseInterface {
    String getToken();
    List<Project> getProjectFromTMSList();
    Configuration getConfiguration();
    void saveConfiguration(Configuration configuration);
}
