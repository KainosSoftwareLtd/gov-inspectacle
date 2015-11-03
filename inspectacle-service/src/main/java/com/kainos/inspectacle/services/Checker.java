package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

import java.util.List;

/**
 * Created by billie on 03/11/2015.
 */
public interface Checker {
    void check(List<ProjectSummary> projects) throws GitLabApiException;
}
