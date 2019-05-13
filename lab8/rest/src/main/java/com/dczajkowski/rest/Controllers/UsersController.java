package com.dczajkowski.rest.Controllers;

import com.dczajkowski.rest.Repositories.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/users")
public class UsersController {
    @Inject
    private UsersRepository usersRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Produces("application/json")
    public String index() throws JsonProcessingException {
        return objectMapper.writeValueAsString(usersRepository.get());
    }
}
