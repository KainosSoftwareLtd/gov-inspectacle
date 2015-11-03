package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

import java.util.List;

public interface ProjectSummariser {
    List<ProjectSummary> getGitlabProjects() throws GitLabApiException;
}
