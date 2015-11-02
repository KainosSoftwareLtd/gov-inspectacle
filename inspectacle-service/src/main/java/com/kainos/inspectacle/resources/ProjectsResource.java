package com.kainos.inspectacle.resources;

import com.codahale.metrics.annotation.Timed;
import com.kainos.inspectacle.services.ProjectSummariser;
import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

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

    public ProjectsResource(ProjectSummariser projectSummariser) {
        this.projectSummariser = projectSummariser;
    }

    @GET
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProjectSummary> get(@PathParam("id") BigInteger id) {
        List<ProjectSummary> projects = projectSummariser.getGitlabProjects();
        return projects;
    }

    @GET
    @Timed
    @Produces(APPLICATION_CSV)
    @Path("/csv")
    public Response getAllTokensCSV() {
        StreamingOutput stream = projectSummariser.createCsvFile(projectSummariser.getGitlabProjects());
        String fileName = projectSummariser.getFileName();
        return Response.ok(stream).header("Content-Disposition", "attachment; filename=" + fileName).build();
    }
}
