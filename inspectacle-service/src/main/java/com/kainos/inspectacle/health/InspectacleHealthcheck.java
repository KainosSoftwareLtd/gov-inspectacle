package com.kainos.inspectacle.health;

import com.codahale.metrics.health.HealthCheck;

public class InspectacleHealthcheck extends HealthCheck {

    public InspectacleHealthcheck() { }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
