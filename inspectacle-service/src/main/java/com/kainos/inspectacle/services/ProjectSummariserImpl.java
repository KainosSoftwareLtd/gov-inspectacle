package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ProjectSummariserImpl implements ProjectSummariser {

    private final GitlabAPI client;
    private final Checker checker;

    public ProjectSummariserImpl(GitlabAPI client, Checker checker) {
        this.client = client;
        this.checker = checker;
    }

    @Override
    public List<ProjectSummary> getGitlabProjects() throws GitLabApiException {
        List<ProjectSummary> allProjects = new ArrayList<>();

        try {
            for (GitlabProject project : client.getProjects()) {
                allProjects.add(new ProjectSummary(project));
            }
        } catch (IOException e) {
            throw new GitLabApiException(e);
        }

        checker.check(allProjects);

        return allProjects;
    }
}