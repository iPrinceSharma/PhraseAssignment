package com.phrase.demo.controller;


import com.phrase.demo.dto.GenericResponse;
import com.phrase.demo.dto.request.TMSConfiguration;
import com.phrase.demo.dto.response.Project;
import com.phrase.demo.service.TMSConfigurationsServiceImpl;
import com.phrase.demo.service.ProjectServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class ProjectRestController {

    private ProjectServiceImpl phraseServiceImpl;
    private TMSConfigurationsServiceImpl configurationsService;

    @RequestMapping(
            method = RequestMethod.GET,
            name = "Get Phrase TMS Account Configuration",
            value = "/getConfiguration",
            produces = { "application/json" }
    )
    @GetMapping()
    public ResponseEntity<GenericResponse> getConfiguration(){
        TMSConfiguration configuration = configurationsService.getConfiguration();
        GenericResponse genericResponse = new GenericResponse("success","TMS Account Configuration details",200,configuration);
        return ResponseEntity.ok().body(genericResponse);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            name = "Save Phrase TMS Account Configuration",
            value = "/saveConfiguration",
            consumes = { "application/json" }
    )
    public ResponseEntity<GenericResponse> saveConfiguration(@RequestBody TMSConfiguration configuration){
        GenericResponse genericResponse;
        if(Objects.isNull(configuration.getUsername()) || Objects.isNull(configuration.getPassword())){
            genericResponse = new GenericResponse("success","Invalid Request Body",422,null);
            return ResponseEntity.unprocessableEntity().body(genericResponse);
        }
        configurationsService.saveConfiguration(configuration);
        genericResponse = new GenericResponse("success","Saved Successfully",201,configuration);
        return ResponseEntity.ok().body(genericResponse);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            name = "Get List of Projects",
            value = "/projects"
    )
    public ResponseEntity<GenericResponse> getProjectList() {
        ResponseEntity.BodyBuilder responseBuilder =  ResponseEntity.ok();
        List<Project> projectList = phraseServiceImpl.getProjectsList();
        GenericResponse genericResponse = new GenericResponse("success","Projects List",200,projectList);
        return responseBuilder.body(genericResponse);
    }
}
