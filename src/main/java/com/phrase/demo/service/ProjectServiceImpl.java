package com.phrase.demo.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.phrase.demo.dto.response.Project;
import com.phrase.demo.errorhandling.ProjectRuntimeException;
import com.phrase.demo.utility.NetworkUtility;
import com.phrase.demo.cutomInterfaces.ProjectInterface;
import lombok.AllArgsConstructor;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectInterface {

    private NetworkUtility networkUtility;

    @Override
    public List<Project> getProjectsList() {
        List<Project> projectList = new ArrayList<>();
        try {
            ResponseBody response = networkUtility.getProjectFromTMSList();
            if(response!=null) {
                String res = response.string();
                JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();
                JsonArray finalRes = jsonObject.getAsJsonArray("content");

                for (int i = 0; i < finalRes.size(); i++) {
                    JsonObject tempObject = finalRes.get(i).getAsJsonObject();
                    String name = String.valueOf(tempObject.getAsJsonPrimitive("name"));
                    String status = String.valueOf(tempObject.getAsJsonPrimitive("status"));
                    String sourceLang = String.valueOf(tempObject.getAsJsonPrimitive("sourceLang"));

                    JsonArray targetLangsArr = tempObject.getAsJsonArray("targetLangs");
                    StringBuilder builder = new StringBuilder();
                    for (int j = 0; j < targetLangsArr.size(); j++) {
                        builder.append(targetLangsArr.get(j).getAsString());
                    }

                    String targetLang = builder.toString().length() != 0 ? builder.toString() : "NA";

                    Project project = new Project();
                    project.setName(name.replace("\"", ""));
                    project.setStatus(status.replace("\"", ""));
                    project.setSourceLanguage(sourceLang.replace("\"", ""));
                    project.setTargetLanguage(targetLang);
                    projectList.add(project);
                }
                return projectList;
            }
        } catch (IOException e) {
            throw new ProjectRuntimeException(e.getMessage(),e);
        }
        return projectList;
    }
}
