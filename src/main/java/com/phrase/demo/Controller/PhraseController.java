package com.phrase.demo.Controller;


import com.phrase.demo.DTO.Configuration;
import com.phrase.demo.DTO.Project;
import com.phrase.demo.DTO.PhraseRepository;
import com.phrase.demo.Service.PhraseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Iterator;
import java.util.List;

@Controller
public class PhraseController {

    @Autowired
    private PhraseServiceImpl phraseServiceImpl;

    @GetMapping(name = "Get Phrase TMS Account Configuration", value = "/getConfiguration")
    public String getConfiguration(Model model){
        Configuration configuration = phraseServiceImpl.getConfiguration();
        model.addAttribute("configuration",configuration);
        return "configuration";
    }

    @PostMapping(name = "Save Phrase TMS Account Configuration",value="/saveConfiguration")
    public String saveConfiguration(Configuration configuration){
        phraseServiceImpl.saveConfiguration(configuration);
        return "update";
    }

    @GetMapping(name = "Get List of Projects",value = "/")
    public String getProjectList(Model model) {
        List<Project> projectList = phraseServiceImpl.getProjectFromTMSList();
        model.addAttribute("projects",projectList);
        return "index";
    }
}
