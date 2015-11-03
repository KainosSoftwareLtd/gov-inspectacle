package com.kainos.inspectacle.resources;

import com.codahale.metrics.annotation.Timed;
import com.kainos.inspectacle.models.inspectacle.ProjectSummary;
import com.kainos.inspectacle.services.GitLabApiException;
import com.kainos.inspectacle.services.OutputFormatter.CsvOutputFormatter;
import com.kainos.inspectacle.services.ProjectSummariser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.math.BigInteger;
import java.util.List;


@Path("/projects")
public class ProjectsResource {
    public final static String APPLICATION_CSV = "txt/csv";
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
    @Produces(APPLICATION_CSV)
    @Path("/csv")
    public Response getAllTokensCSV() throws GitLabApiException {
        StreamingOutput stream = this.csvOutputFormatter.getOutput(projectSummariser.getGitlabProjects());
        String fileName = "Repos_" + System.currentTimeMillis() + ".csv";
        return Response.ok(stream).header("Content-Disposition", "attachment; filename=" + fileName).build();
    }
}
