package com.kainos.inspectacle.resources;

import com.kainos.inspectacle.models.inspectacle.MediaType;
import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import com.kainos.inspectacle.services.GitLabApiException;
import com.kainos.inspectacle.services.ProjectSummariser;
import com.kainos.inspectacle.services.outputformatter.CsvOutputFormatter;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.math.BigInteger;
import java.util.List;


@Path("/projects")
public class ProjectsResource {
    private final ProjectSummariser projectSummariser;
    private final CsvOutputFormatter csvOutputFormatter;

    public ProjectsResource(ProjectSummariser projectSummariser, CsvOutputFormatter csvOutputFormatter) {
        this.projectSummariser = projectSummariser;
        this.csvOutputFormatter = csvOutputFormatter;
    }

    @GET
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectSummary> get(@PathParam("id") BigInteger id) throws GitLabApiException {
        return projectSummariser.getGitlabProjects();
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_CSV)
    @Path("/csv")
    public Response getAllTokensCSV() throws GitLabApiException {
        StreamingOutput stream = this.csvOutputFormatter.getOutput(projectSummariser.getGitlabProjects());
        String fileName = "Repos_" + System.currentTimeMillis() + ".csv";
        return Response.ok(stream).header("Content-Disposition", "attachment; filename=" + fileName).build();
    }
}
