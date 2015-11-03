package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

import java.util.List;

public interface Checker {
    void check(List<ProjectSummary> projects) throws GitLabApiException;
}
