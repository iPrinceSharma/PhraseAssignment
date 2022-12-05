package com.phrase.demo.Utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.phrase.demo.DTO.Configuration;
import com.phrase.demo.DTO.LoginInfo;
import com.phrase.demo.DTO.Project;
import com.phrase.demo.Service.PhraseServiceImpl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Component
public class NetworkUtility {
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    public static final String BASE_URL= "https://cloud.memsource.com/web";

    public LoginInfo authRequest(Configuration configuration) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String authUrl = BASE_URL + "/api2/v1/auth/login";

        JsonObject json = new JsonObject();
        json.addProperty("userName",configuration.getUsername());
        json.addProperty("password",configuration.getPassword());
        json.addProperty("code","NA");

        RequestBody body = RequestBody.create(json.toString(), JSON);
        Request request = new Request.Builder()
                .url(authUrl)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()){
                String loginResponse = response.body().string() ;
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(loginResponse, new TypeReference<>() {});
            }
            // TODO throw error in case of response other than 200
            return new LoginInfo();
        }
    }

    public List<Project> getProjectsList(String token) throws IOException{
        OkHttpClient client = new OkHttpClient();

        String projectListUrl = BASE_URL + "/api2/v1/projects";
        String auth_token="ApiToken "+token;

        Request request = new Request.Builder()
                .header("Authorization", auth_token)
                .url(projectListUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ArrayList<Project> resultList = new ArrayList<>();
            if (response.isSuccessful()){

                String res = response.body().string();
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
                    project.setName(name.replace("\"",""));
                    project.setStatus(status.replace("\"",""));
                    project.setSourceLanguage(sourceLang.replace("\"",""));
                    project.setTargetLanguage(targetLang);
                    resultList.add(project);
                }
                return resultList;
            }

           // TODO throw error in case of response other than 200
           // in case of error just return  the empty list for now
           return new ArrayList<>();
        }
    }

}
