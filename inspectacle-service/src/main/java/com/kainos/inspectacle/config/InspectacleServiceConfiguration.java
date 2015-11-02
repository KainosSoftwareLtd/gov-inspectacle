package com.kainos.inspectacle.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class InspectacleServiceConfiguration extends Configuration {
    @Valid
    @NotNull
    @JsonProperty
    private ApplicationConfig applicationConfig = new ApplicationConfig();

    public ApplicationConfig getApplicationConfig() {
        return applicationConfig;
    }

    @Valid
    @NotNull
    @JsonProperty
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }

    @Valid
    @JsonProperty
    private String privateKey;

    public String getPrivateKey() {
        return privateKey;
    }

    @Valid
    @JsonProperty
    private String url;

    public String getURL() {
        return url;
    }    
}
