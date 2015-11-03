package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import com.kainos.inspectacle.services.checks.Check;
import com.kainos.inspectacle.services.checks.Checks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckerImpl implements Checker {
    private final Map<Checks, Check> checks;

    public CheckerImpl(Map<Checks, Check> checks) {
        this.checks = checks;
    }

    @Override
    public void check(List<ProjectSummary> projects) throws GitLabApiException {
        for (ProjectSummary projectSummary : projects) {

            Map<Checks, Boolean> checkResult = new HashMap<>();

            for (Checks checkName : checks.keySet()) {
                checkResult.put(checkName, checks.get(checkName).check(projectSummary));
            }

            projectSummary.setCheckResults(checkResult);
        }
    }
}
