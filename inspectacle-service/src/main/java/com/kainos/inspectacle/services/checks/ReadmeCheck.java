package com.kainos.inspectacle.services.checks;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import com.kainos.inspectacle.services.GitLabApiException;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabRepositoryTree;

import java.io.IOException;
import java.util.List;

public class ReadmeCheck implements Check {
    private final GitlabAPI client;

    public ReadmeCheck(GitlabAPI client) {
        this.client = client;
    }

    @Override
    public boolean check(ProjectSummary project) throws GitLabApiException {
        boolean containsFile = false;
        try {
            List<GitlabRepositoryTree> repoTree = client.getRepositoryTree(project.getGitLabProject(), "/", "master");

            for (GitlabRepositoryTree treeItem : repoTree) {
                if (treeItem.getName().equalsIgnoreCase("readme.md") || treeItem.getName().equalsIgnoreCase("readme")) {
                    containsFile = true;
                    break;
                }
            }
        } catch (IOException e) {
            throw new GitLabApiException(e);
        }

        return containsFile;
    }

}
