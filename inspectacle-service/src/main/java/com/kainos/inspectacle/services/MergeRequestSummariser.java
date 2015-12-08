package com.kainos.inspectacle.services;

import com.kainos.inspectacle.models.inspectacle.MergeRequestSummary;

import java.util.List;

/**
 * Created by Rory80hz on 03/12/2015.
 */
public interface MergeRequestSummariser {
    List<MergeRequestSummary> getMergeRequestSummaries() throws GitLabApiException;

    MergeRequestSummary getMergeRequestSummary(Integer projectId, Integer mergeRequestId) throws GitLabApiException;
}
