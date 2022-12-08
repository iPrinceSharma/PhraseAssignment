package com.phrase.demo.controller;

import com.google.gson.JsonObject;
import com.phrase.demo.dto.request.TMSConfiguration;
import com.phrase.demo.dto.response.Project;
import com.phrase.demo.service.ProjectServiceImpl;
import com.phrase.demo.service.TMSConfigurationsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class ProjectControllerTests {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @MockBean
    private ProjectServiceImpl projectService;

    @MockBean
    private TMSConfigurationsServiceImpl configurationsService;

    @BeforeEach
    public void setup(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    @DisplayName("Get All Projects List")
    public void getAllProjectsAPI() throws Exception {
        List<Project> projectList = new ArrayList<>(){{
                add(new Project("Example 1","New","English","English"));
            }
        };
        when(projectService.getProjectsList()).thenReturn(projectList);
        mvc.perform(get("/projects"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNotEmpty());
    }

    @Test
    @DisplayName("Get TMS Configurations Details")
    public void getTMSConfigurationsAPI() throws Exception {
        TMSConfiguration configuration = new TMSConfiguration();
        configuration.setId(1L);
        configuration.setPassword("password");
        configuration.setUsername("username");

        when(configurationsService.getConfiguration()).thenReturn(configuration);
        mvc.perform(get("/getConfiguration"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.username").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data.password").isNotEmpty());
    }

}
