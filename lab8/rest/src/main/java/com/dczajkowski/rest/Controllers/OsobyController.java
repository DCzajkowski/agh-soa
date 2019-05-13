package com.dczajkowski.rest.Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("osoby")
public class OsobyController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response index() throws URISyntaxException {
        return Response.status(308).location(new URI("/users")).build();
    }
}
