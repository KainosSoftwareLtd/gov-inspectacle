package com.kainos.inspectacle.models.inspectacle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectSummary {
    private Integer id;
    private String name;

    private String description;
    private String path;

    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;

    @JsonProperty("archived")
    private boolean archived;

    @JsonProperty("default_branch")
    private String defaultBranch;

    private boolean containsReadMe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    public void setPathWithNamespace(String pathWithNamespace) {
        this.pathWithNamespace = pathWithNamespace;
    }


    public boolean getContainsReadMe() {
        return containsReadMe;
    }

    public void setContainsReadMe(boolean containsReadMe) {
        this.containsReadMe = containsReadMe;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public void setDefaultBranch(String defaultBranch) {
        this.defaultBranch = defaultBranch;
    }
}