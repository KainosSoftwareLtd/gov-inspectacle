package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.MergeRequestSummary;
import org.gitlab.api.GitlabAPI;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rory80hz on 03/12/2015.
 */
public class MergeRequestSummariserImpl implements MergeRequestSummariser {

    private final GitlabAPI client;

    public MergeRequestSummariserImpl(GitlabAPI client) {
        this.client = client;
    }

    @Override
    public List<MergeRequestSummary> getMergeRequestSummaries() throws GitLabApiException {
        List<MergeRequestSummary> mergeRequestSummaries = new ArrayList<>();

        try {
            for (GitlabProject project : client.getProjects()) {
                for (GitlabMergeRequest mergeRequest : client.getOpenMergeRequests(project)) {
                    mergeRequestSummaries.add(new MergeRequestSummary(project, mergeRequest));
                }
            }
        } catch (IOException e) {
            throw new GitLabApiException(e);
        }
        return mergeRequestSummaries;
    }
}
