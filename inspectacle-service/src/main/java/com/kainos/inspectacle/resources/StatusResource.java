package com.kainos.inspectacle.resources;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/status")
public class StatusResource {

    @GET
    @Timed
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        return "running";
    }
}