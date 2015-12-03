package com.kainos.inspectacle.services.outputformatter;

import com.kainos.inspectacle.models.inspectacle.ProjectSummary;

import com.google.common.base.Joiner;

import javax.ws.rs.core.StreamingOutput;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;


public class CsvOutputFormatter implements OutputFormatter {

    private static final String CSV_HEADER = "NAME,PATH,DESCRIPTION,README,ARCHIVED,DEFAULT_BRANCH";

    @Override
    public StreamingOutput getOutput(final List<ProjectSummary> projectSummaries) {
        return new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws OutputException {
                try (Writer writer = new BufferedWriter(new OutputStreamWriter(os))) {
                    writer.write(CSV_HEADER + "\n");
                    for (ProjectSummary projectSummary : projectSummaries) {
                        String line = Joiner.on(',').useForNull("").join(projectSummary.getName(), projectSummary.getPathWithNamespace(), projectSummary.getDescription(), projectSummary.getContainsReadMe(), projectSummary.isArchived(), projectSummary.getDefaultBranch()) + "\n";
                        writer.write(line);
                    }
                } catch (IOException ioException) {
                    throw new OutputException("Error generating CSV file", ioException);
                }
            }
        };
    }
}
