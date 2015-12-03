package com.kainos.inspectacle;

import com.kainos.inspectacle.config.InspectacleServiceConfiguration;
import com.kainos.inspectacle.health.InspectacleHealthcheck;
import com.kainos.inspectacle.resources.MergeRequestTrackerResource;
import com.kainos.inspectacle.resources.ProjectsResource;
import com.kainos.inspectacle.resources.StatusResource;
import com.kainos.inspectacle.services.Checker;
import com.kainos.inspectacle.services.CheckerImpl;
import com.kainos.inspectacle.services.MergeRequestSummariserImpl;
import com.kainos.inspectacle.services.ProjectSummariserImpl;
import com.kainos.inspectacle.services.checks.Check;
import com.kainos.inspectacle.services.checks.Checks;
import com.kainos.inspectacle.services.checks.ReadmeCheck;
import com.kainos.inspectacle.services.outputformatter.CsvOutputFormatter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.gitlab.api.GitlabAPI;

import java.util.HashMap;
import java.util.Map;


public class InspectacleApplication extends Application<InspectacleServiceConfiguration> {

    private static final String INSPECTACLE_SERVICE = "inspectacle";

    public static void main(String[] args) throws Exception {
        new InspectacleApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<InspectacleServiceConfiguration> bootstrap) {
    }

    @Override
    public void run(InspectacleServiceConfiguration configuration, Environment environment) throws Exception {
        GitlabAPI gitLabAPI = GitlabAPI.connect(configuration.getURL(), configuration.getPrivateKey());

        Map<Checks, Check> checks = new HashMap<>();
        checks.put(Checks.README, new ReadmeCheck(gitLabAPI));

        Checker checker = new CheckerImpl(checks);

        environment.jersey().register(new ProjectsResource(new ProjectSummariserImpl(gitLabAPI, checker), new CsvOutputFormatter()));
        environment.jersey().register(new MergeRequestTrackerResource(new MergeRequestSummariserImpl(gitLabAPI)));
        environment.jersey().register(new StatusResource());
        environment.healthChecks().register(getName(), new InspectacleHealthcheck());
    }

    @Override
    public String getName() {
        return INSPECTACLE_SERVICE;
    }
}