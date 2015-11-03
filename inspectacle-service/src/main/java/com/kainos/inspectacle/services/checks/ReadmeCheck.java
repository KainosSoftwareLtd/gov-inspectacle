package com.kainos.inspectacle.services.checks;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import com.kainos.inspectacle.services.GitLabApiException;

import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabRepositoryTree;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadmeCheck implements Check {
    public static final String README_MD = "README.md";
    public static final String README_STANDARD = "README";
    private final GitlabAPI client;
    private final List<String> validReadmeNames = new ArrayList<>();

    public ReadmeCheck(GitlabAPI client) {
        this.client = client;

        validReadmeNames.add(README_MD.toLowerCase());
        validReadmeNames.add(README_STANDARD.toLowerCase());
    }

    @Override
    public boolean check(ProjectSummary project) throws GitLabApiException {
        boolean containsFile = false;
        
        try {
            GitlabProject gitlabProject = project.getGitLabProject();

            try {
                List<GitlabRepositoryTree> repoTree = client.getRepositoryTree(gitlabProject, "/", null);

                for (GitlabRepositoryTree treeItem : repoTree) {
                    if (validReadmeNames.contains(treeItem.getName().toLowerCase())) {
                        containsFile = true;
                        break;
                    }
                }
            } catch (FileNotFoundException e) {
                // We do nothing here because this means we don't have any data in the git tree, i.e. no commits.
                // This means we can safely assume that this test is failed.
            }
        } catch (IOException e) {
            throw new GitLabApiException(e);
        }

        return containsFile;
    }

}
