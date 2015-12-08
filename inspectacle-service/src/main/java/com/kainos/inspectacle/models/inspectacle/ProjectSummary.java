package com.kainos.inspectacle.models.inspectacle;

import com.kainos.inspectacle.services.checks.Checks;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gitlab.api.models.GitlabProject;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true, value="gitLabProject")
public class ProjectSummary {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("path")
    private String path;

    @JsonProperty("path_with_namespace")
    private String pathWithNamespace;

    @JsonProperty("archived")
    private boolean archived;

    @JsonProperty("default_branch")
    private String defaultBranch;

    private GitlabProject gitLabProject;
    private Map<Checks, Boolean> checkResults;

    public ProjectSummary(GitlabProject project) {
        this.setFromGitlabProject(project);
    }

    private void setFromGitlabProject(GitlabProject project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.path = project.getPath();
        this.pathWithNamespace = project.getPathWithNamespace();
        this.archived = project.isArchived();
        this.defaultBranch = project.getDefaultBranch();
        this.gitLabProject = project;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPathWithNamespace() {
        return pathWithNamespace;
    }

    @JsonProperty("containsReadme")
    public boolean getContainsReadMe() {
        return checkResults.get(Checks.README);
    }

    public boolean isArchived() {
        return archived;
    }

    public String getDefaultBranch() {
        return defaultBranch;
    }

    public GitlabProject getGitLabProject() {
        return gitLabProject;
    }

    public void setCheckResults(Map<Checks, Boolean> checkResults) {
        this.checkResults = checkResults;
    }
}