package com.kainos.inspectacle;

import com.kainos.inspectacle.config.InspectacleServiceConfiguration;
import com.kainos.inspectacle.health.InspectacleHealthcheck;
import com.kainos.inspectacle.resources.ProjectsResource;
import com.kainos.inspectacle.services.ProjectSummariser;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.kainos.inspectacle.resources.StatusResource;

import javax.ws.rs.client.Client;


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
        final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
        environment.jersey().register(new ProjectsResource(new ProjectSummariser(client, configuration.getPrivateKey(),configuration.getURL())));
        environment.jersey().register(new StatusResource(getName()));
        environment.healthChecks().register(getName(), new InspectacleHealthcheck());
    }

    @Override
    public String getName() {
        return INSPECTACLE_SERVICE;
    }
}