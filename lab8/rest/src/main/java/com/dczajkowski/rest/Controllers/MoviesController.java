package com.dczajkowski.rest.Controllers;

import com.dczajkowski.rest.Models.Movie;
import com.dczajkowski.rest.Repositories.MoviesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.activation.MimeType;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("movies")
public class MoviesController {
    @Inject
    private MoviesRepository moviesRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Movie> getMovies(String title) {
        return (title != null && !title.isEmpty())
            ? moviesRepository.getByTitle(title)
            : moviesRepository.get();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String indexJson(@QueryParam("title") String title) throws JsonProcessingException {
        return objectMapper.writeValueAsString(getMovies(title));
    }

    @GET
    @Produces("text/uri-list")
    public String indexUris(@QueryParam("title") String title) throws JsonProcessingException {
        return objectMapper.writeValueAsString(getMovies(title).stream().map(Movie::getUrl).collect(Collectors.toList()));
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String indexText(@QueryParam("title") String title) {
        return getMovies(title)
            .stream()
            .map(movie -> "\"" + movie.getTitle() + "\"\n" + movie.getUrl() + "\n")
            .reduce("", (String acc, String movie) -> acc + movie + "\n");
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        Movie movie = moviesRepository.find(id);

        if (movie == null) {
            return Response.status(404).build();
        }

        return Response.ok(movie).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Movie store(Movie movie) {
        moviesRepository.create(movie);

        return movie;
    }

    @PATCH
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Movie movie_) {
        Movie movie = moviesRepository.find(id);

        if (movie == null) {
            return Response.status(422).build();
        }

        movie.setTitle((movie_.getTitle() != null) ? movie_.getTitle() : movie.getTitle());
        movie.setUrl((movie_.getUrl() != null) ? movie_.getUrl() : movie.getUrl());

        moviesRepository.update(movie);

        return Response.ok(movie).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response destroy(@PathParam("id") int id) {
        boolean successful = moviesRepository.remove(id);

        if (!successful) {
            return Response.status(422).build();
        }

        return Response.noContent().build();
    }
}
