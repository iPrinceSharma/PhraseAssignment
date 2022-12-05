package com.phrase.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Project {
    public String name;
    public String status;
    public String sourceLanguage;
    public String targetLanguage;

    public Project(){}

    public Project(String name, String status,String sourceLanguage,String targetLanguage){
        this.name = name;
        this.status = status;
        this.sourceLanguage = sourceLanguage;
        this.targetLanguage = targetLanguage;
    }
}

