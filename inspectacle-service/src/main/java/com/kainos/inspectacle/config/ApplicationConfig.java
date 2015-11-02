package com.kainos.inspectacle.config;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ApplicationConfig {

    @JsonProperty
    private String applicationVersionFilePath;

    public String getApplicationVersionFilePath() {
        return applicationVersionFilePath;
    }
}
