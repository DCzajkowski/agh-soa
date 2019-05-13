package com.dczajkowski.rest.Controllers;

import com.dczajkowski.rest.Models.User;
import com.dczajkowski.rest.Repositories.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("users")
public class UsersController {
    @Inject
    private UsersRepository usersRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String index() throws JsonProcessingException {
        return objectMapper.writeValueAsString(usersRepository.get());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        User user = usersRepository.find(id);

        if (user == null) {
            return Response.status(404).build();
        }

        return Response.ok(user).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User store(User user) {
        usersRepository.create(user);

        return user;
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, User user_) {
        User user = usersRepository.find(id);

        if (user == null) {
            return Response.status(422).build();
        }

        user.setName((user_.getName() != null) ? user_.getName() : user.getName());
        user.setAge((user_.getAge() != 0) ? user_.getAge() : user.getAge());
        user.setAvatar((user_.getAvatar() != null) ? user_.getAvatar() : user.getAvatar());

        usersRepository.update(user);

        return Response.ok(user).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response destroy(@PathParam("id") int id) {
        boolean successful = usersRepository.remove(id);

        if (!successful) {
            return Response.status(422).build();
        }

        return Response.noContent().build();
    }
}
