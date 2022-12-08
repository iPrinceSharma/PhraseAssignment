package com.phrase.demo.utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.phrase.demo.dto.request.TMSConfiguration;
import com.phrase.demo.dto.response.LoginInfo;
import com.phrase.demo.errorhandling.ProjectRuntimeException;
import com.phrase.demo.service.TMSConfigurationsServiceImpl;
import lombok.AllArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
@AllArgsConstructor
public class NetworkUtility {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static final String GET_AUTH_TOKEN= "https://cloud.memsource.com/web/api2/v1/auth/login";
    public static final String GET_PROJECTS_LIST= "https://cloud.memsource.com/web/api2/v1/projects";

    private TMSConfigurationsServiceImpl configurationsService;

    public String getToken(TMSConfiguration configuration) throws IOException {
        OkHttpClient client = new OkHttpClient();

        JsonObject json = new JsonObject();
        json.addProperty("userName",configuration.getUsername());
        json.addProperty("password",configuration.getPassword());
        json.addProperty("code","NA");

        RequestBody body = RequestBody.create(configuration.toString(), JSON);
        Request request = new Request.Builder()
                .url(GET_AUTH_TOKEN)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()){
                String loginResponse = response.body().string() ;
                ObjectMapper mapper = new ObjectMapper();
                LoginInfo loginInfo =  mapper.readValue(loginResponse, new TypeReference<>() {});
                return loginInfo.getToken();
            }
            throw new ProjectRuntimeException(String.valueOf(response.code()),response.message());
        }catch (ProjectRuntimeException e){
            throw new ProjectRuntimeException(e.getCode(),e.getMessage());
        }
    }

    public ResponseBody getProjectFromTMSList() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String auth_token="ApiToken "+getToken(configurationsService.getConfiguration());

        Request request = new Request.Builder()
                .header("Authorization", auth_token)
                .url(GET_PROJECTS_LIST)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.isSuccessful()){
                return response.body();
            }
            throw new ProjectRuntimeException(String.valueOf(response.code()),response.message());
        }catch (ProjectRuntimeException e){
            throw new ProjectRuntimeException(e.getCode(),e.getMessage());
        }
    }

}
