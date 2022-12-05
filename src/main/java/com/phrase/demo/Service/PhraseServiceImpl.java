package com.phrase.demo.Service;

import com.phrase.demo.DTO.Configuration;
import com.phrase.demo.DTO.LoginInfo;
import com.phrase.demo.DTO.PhraseRepository;
import com.phrase.demo.DTO.Project;
import com.phrase.demo.Utility.NetworkUtility;
import com.phrase.demo.PhraseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PhraseServiceImpl implements PhraseInterface {

    @Autowired
    private NetworkUtility networkUtility;

    @Autowired
    private PhraseRepository phraseRepository;

    @Override
    public String getToken() {
        try {
            LoginInfo response  = networkUtility.authRequest(getConfiguration());
            return response.getToken();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> getProjectFromTMSList() {
        List<Project> projectList = new ArrayList<>();
        try {
            projectList = networkUtility.getProjectsList(getToken());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    @Override
    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Iterable<Configuration> configuration1= phraseRepository.findAll();
        Iterator<Configuration> iterator = configuration1.iterator();
        if(iterator.hasNext()){
            // if entry found in the database then update the object
            configuration = iterator.next();
        }
        return configuration;
    }

    @Override
    public void saveConfiguration(Configuration configuration) {
        phraseRepository.save(configuration);
    }
}
