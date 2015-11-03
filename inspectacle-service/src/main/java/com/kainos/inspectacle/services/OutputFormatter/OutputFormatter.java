package com.kainos.inspectacle.services.OutputFormatter;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

import javax.ws.rs.core.StreamingOutput;
import java.util.List;

public interface OutputFormatter {
    StreamingOutput getOutput(List<ProjectSummary> projectSummaries);
}
