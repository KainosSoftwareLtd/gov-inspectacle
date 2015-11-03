package com.kainos.inspectacle.services.checks;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import com.kainos.inspectacle.services.GitLabApiException;

public interface Check {
    boolean check(ProjectSummary project) throws GitLabApiException;
}
