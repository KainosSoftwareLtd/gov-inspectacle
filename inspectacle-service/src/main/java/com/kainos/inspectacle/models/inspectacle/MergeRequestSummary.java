package com.kainos.inspectacle.models.inspectacle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabProject;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeRequestSummary {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("projectName")
    private String projectName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("upVotes")
    private Integer upVotes;

    @JsonProperty("createdBy")
    private String createdBy;

    @JsonProperty("assignedTo")
    private String assignedTo;

    @JsonProperty("upDatedAt")
    private Date updatedAt;

    @JsonProperty("commenters")
    private List<String> commenters;

    public MergeRequestSummary(GitlabProject project, GitlabMergeRequest gitlabMergeRequest) {
        this.setFromProjectAndMergeRequest(project, gitlabMergeRequest);
    }

    private void setFromProjectAndMergeRequest(GitlabProject project, GitlabMergeRequest gitlabMergeRequest) {
        this.id = project.getId();
        this.projectName = project.getName();
        this.description = gitlabMergeRequest.getDescription();
        this.upVotes = gitlabMergeRequest.getUpvotes();
        this.updatedAt = gitlabMergeRequest.getUpdatedAt();

        if (gitlabMergeRequest.getAuthor() != null) {
            this.createdBy = gitlabMergeRequest.getAuthor().getName();
        }

        if(gitlabMergeRequest.getAssignee() != null){
            this.assignedTo = gitlabMergeRequest.getAssignee().getName();
        }
    }
}