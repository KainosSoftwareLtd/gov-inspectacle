package com.kainos.inspectacle.services;

import com.google.common.base.Joiner;
import com.kainos.inspectacle.models.gitlab.GitlabRepositoryTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;
import java.util.Arrays;
import java.util.List;


public class ProjectSummariser {

    private static Logger LOGGER = LoggerFactory.getLogger(ProjectSummariser.class);
    private static String PRIVATE_TOKEN = "PRIVATE-TOKEN";
    private final Client client;
    private final String gitlabToken;
    private static final String CSV_HEADER = "NAME,PATH,DESCRIPTION,README,ARCHIVED,DEFAULT_BRANCH";
    private final String url;

    public ProjectSummariser(Client client, String gitlabToken, String url) {
        this.client = client;
        this.gitlabToken = gitlabToken;
        this.url = url;
    }

    public List<ProjectSummary> getGitlabProjects() {
        List<ProjectSummary> allProjects = com.google.common.collect.Lists.newArrayList();
        ProjectSummary[] response = null;
        Integer page = 1;

        do {
            try {
                String target = String.format("%s/api/v3/projects?per_page=100&page=%s",this.url, page.toString());
                LOGGER.debug("TARGET " + target);
                Invocation thing = client.target(target)
                        .request()
                        .accept(MediaType.APPLICATION_JSON_TYPE)
                        .header(PRIVATE_TOKEN, this.gitlabToken)
                        .buildGet();

                response = thing.invoke(ProjectSummary[].class);

                if (response != null && response.length > 0) {
                    allProjects.addAll(Arrays.asList(response));
                    LOGGER.debug("Content on Page " + page);
                }
                page += 1;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        while (response.length > 0);

        setReadmeStatusForProjects(allProjects);
        return allProjects;
    }

    private void setReadmeStatusForProjects(List<ProjectSummary> projects) {
        for (ProjectSummary projectSummary : projects) {
            projectSummary.setContainsReadMe(checkForReadmeFile(projectSummary.getId()));
            LOGGER.debug("Project: " + projectSummary.getName() + "Readme: " + projectSummary.getContainsReadMe());
        }
    }

    private boolean checkForReadmeFile(Integer projectId) {
        boolean containsFile = false;
        try {
            String target = String.format("%s/api/v3/projects/%d/repository/tree", this.url, projectId);
            Invocation thing = client.target(target)
                    .request()
                    .accept(MediaType.APPLICATION_JSON_TYPE)
                    .header(PRIVATE_TOKEN, gitlabToken)
                    .buildGet();

            GitlabRepositoryTree[] response = thing.invoke(GitlabRepositoryTree[].class);

            for (GitlabRepositoryTree treeItem : Arrays.asList(response)) {
                if (treeItem.getName().equalsIgnoreCase("readme.md") || treeItem.getName().equalsIgnoreCase("readme")) {
                    containsFile = true;
                    break;
                }
            }
        } catch (Exception e) {
            LOGGER.debug("Failed to get Project: " + projectId);
        }

        return containsFile;
    }

    public StreamingOutput createCsvFile(final List<ProjectSummary> projectSummaries) {
        return new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException, WebApplicationException {
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(os))) {
                    writer.write(CSV_HEADER + "\n");
                    for (ProjectSummary projectSummary : projectSummaries) {
                        String line = Joiner.on(',').useForNull("").join(projectSummary.getName(), projectSummary.getPathWithNamespace(), projectSummary.getDescription(), projectSummary.getContainsReadMe(), projectSummary.isArchived(), projectSummary.getDefaultBranch()) + "\n";
                        writer.write(line);
                    }
                } catch (IOException ioException) {
                    LOGGER.error("Failed to create CSV file\n%s", ioException.getMessage());
                    throw new WebApplicationException("Error generating CSV file", Response.Status.INTERNAL_SERVER_ERROR);
                }
            }
        };
    }

    public String getFileName() {
        return "Repos_" + System.currentTimeMillis() + ".csv";
    }
}