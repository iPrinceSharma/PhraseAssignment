package com.phrase.demo.controller;

import com.phrase.demo.dto.request.TMSConfiguration;
import com.phrase.demo.dto.response.Project;
import com.phrase.demo.service.ProjectServiceImpl;
import com.phrase.demo.service.TMSConfigurationsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("mvc")
@AllArgsConstructor
public class ProjectMvcController {

    private ProjectServiceImpl projectService;
    private TMSConfigurationsServiceImpl configurationsService;

    @GetMapping(name = "Get Phrase TMS Account Configuration", value = "/getConfiguration")
    public String getConfiguration(Model model){
        TMSConfiguration configuration = configurationsService.getConfiguration();
        model.addAttribute("configuration",configuration);
        return "configuration";
    }

    @PostMapping(name = "Save Phrase TMS Account Configuration",value="/saveConfiguration")
    public String saveConfiguration(TMSConfiguration configuration){
        configurationsService.saveConfiguration(configuration);
        return "update";
    }

    @GetMapping(name = "Get List of Projects",value = "/")
    public String getProjectList(Model model) {
        List<Project> projectList = projectService.getProjectsList();
        model.addAttribute("projects",projectList);
        return "index";
    }
}
