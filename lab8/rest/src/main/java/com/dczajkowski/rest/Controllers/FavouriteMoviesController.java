package com.dczajkowski.rest.Controllers;

import com.dczajkowski.rest.Models.Movie;
import com.dczajkowski.rest.Models.User;
import com.dczajkowski.rest.Repositories.MoviesRepository;
import com.dczajkowski.rest.Repositories.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("users")
public class FavouriteMoviesController {
    @Inject
    private UsersRepository usersRepository;

    @Inject
    private MoviesRepository moviesRepository;

    @POST
    @Path("/{user_id}/favourites/movies/{movie_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response favourite(
        @PathParam("user_id") int userId,
        @PathParam("movie_id") int movieId
    ) {
        User user = usersRepository.find(userId);
        Movie movie = moviesRepository.find(movieId);

        if (user == null || movie == null) {
            return Response.status(404).build();
        }

        List<Movie> favouriteMovies = new ArrayList<>(user.getFavouriteMovies());
        favouriteMovies.add(movie);

        user.setFavouriteMovies(favouriteMovies);

        usersRepository.update(user);

        return Response.status(201).entity(user).build();
    }

    @DELETE
    @Path("/{user_id}/favourites/movies/{movie_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response unfavourite(
        @PathParam("user_id") int userId,
        @PathParam("movie_id") int movieId
    ) {
        User user = usersRepository.find(userId);

        if (user == null) {
            return Response.status(404).build();
        }

        user.setFavouriteMovies(
            new ArrayList<>(user.getFavouriteMovies())
                .stream()
                .filter(movie -> movie.getId() != movieId)
                .collect(Collectors.toList())
        );

        usersRepository.update(user);

        return Response.status(201).entity(user).build();
    }
}
