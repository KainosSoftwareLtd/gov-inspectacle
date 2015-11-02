package com.kainos.inspectacle.models.gitlab;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitlabSession extends GitlabUser {

    public static final String URL = "/session";

    @JsonProperty("private_token")
    private String privateToken;

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

}
