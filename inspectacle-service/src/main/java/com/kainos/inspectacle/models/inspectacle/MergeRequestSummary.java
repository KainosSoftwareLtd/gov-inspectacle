package com.kainos.inspectacle.models.inspectacle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.gitlab.api.models.GitlabMergeRequest;
import org.gitlab.api.models.GitlabNote;
import org.gitlab.api.models.GitlabProject;
import org.gitlab.api.models.GitlabUser;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MergeRequestSummary {
    @JsonProperty("projectId")
    private Integer projectId;

    @JsonProperty("mergeRequestId")
    private Integer mergeRequestId;

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
    private HashSet<String> commenters = new HashSet<>();

    @JsonProperty("passed")
    private Boolean passed;

    public MergeRequestSummary(GitlabProject project, GitlabMergeRequest gitlabMergeRequest, List<GitlabNote> notes) {
        this.setFromProjectMergeRequestAndNotes(project, gitlabMergeRequest, notes);
    }

    private void setFromProjectMergeRequestAndNotes(GitlabProject gitlabProject, GitlabMergeRequest gitlabMergeRequest, List<GitlabNote> notes) {
        this.projectId = gitlabProject.getId();
        this.mergeRequestId = gitlabMergeRequest.getId();
        this.projectName = gitlabProject.getName();
        this.description = gitlabMergeRequest.getDescription();
        this.upVotes = gitlabMergeRequest.getUpvotes();
        this.updatedAt = gitlabMergeRequest.getUpdatedAt();
        this.createdBy = getMergeRequestAuthor(gitlabMergeRequest);
        this.assignedTo = getMergeRequestAssignee(gitlabMergeRequest);
        this.commenters = getCommenterList(notes);
        this.upVotes = getUpVoteCount(notes, gitlabMergeRequest);
        this.passed = this.upVotes >= 2;
    }

    private String getMergeRequestAuthor(GitlabMergeRequest gitlabMergeRequest) {
        String createdBy = null;
        if (gitlabMergeRequest.getAuthor() != null) {
            createdBy = gitlabMergeRequest.getAuthor().getName();
        }
        return createdBy;
    }

    private String getMergeRequestAssignee(GitlabMergeRequest gitlabMergeRequest) {
        String assignedTo = null;
        if (gitlabMergeRequest.getAssignee() != null) {
            assignedTo = gitlabMergeRequest.getAssignee().getName();
        }
        return assignedTo;
    }

    private HashSet<String> getCommenterList(List<GitlabNote> notes) {
        HashSet<String> commenterList = new HashSet<>();
        for (GitlabNote note : notes) {
            if (note.getAuthor() != null) {
                commenterList.add(note.getAuthor().getName());
            }
        }
        return commenterList;
    }

    private Integer getUpVoteCount(List<GitlabNote> notes, GitlabMergeRequest mergeRequest) {
        Integer upVoteCount = 0;
        for (GitlabNote note : notes) {
            if (checkIfUpvote(note, mergeRequest.getAuthor())) {
                upVoteCount += 1;
            }
        }
        return upVoteCount;
    }

    private boolean checkIfUpvote(GitlabNote note, GitlabUser author) {
        boolean isUpVote = false;
        if (!note.getAuthor().getId().equals(author.getId())) {
            if (note.getBody().startsWith(":+1:")) {
                isUpVote = true;
            }
        }
        return isUpVote;
    }
}