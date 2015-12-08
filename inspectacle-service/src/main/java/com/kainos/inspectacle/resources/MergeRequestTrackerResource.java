package com.kainos.inspectacle.resources;

import com.codahale.metrics.annotation.Timed;
import com.kainos.inspectacle.models.inspectacle.MediaType;
import com.kainos.inspectacle.models.inspectacle.MergeRequestSummary;
import com.kainos.inspectacle.services.GitLabApiException;
import com.kainos.inspectacle.services.MergeRequestSummariser;

import javax.ws.rs.*;
import java.util.List;


@Path("/merge-requests")
public class MergeRequestTrackerResource {
    private final MergeRequestSummariser mergeRequestSummariser;

    public MergeRequestTrackerResource(MergeRequestSummariser mergeRequestSummariser) {
        this.mergeRequestSummariser = mergeRequestSummariser;
    }

    @GET
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<MergeRequestSummary> get() throws GitLabApiException {
        return mergeRequestSummariser.getMergeRequestSummaries();
    }

    @GET
    @Timed
    @Path("/project/{projectId}/mergeRequestId/{mergeRequestId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MergeRequestSummary get(@PathParam("projectId") Integer projectId, @PathParam("mergeRequestId") Integer mergeRequestId) throws GitLabApiException {
        return mergeRequestSummariser.getMergeRequestSummary(projectId, mergeRequestId);
    }
}