package Repositories;

import Models.Movie;
import Models.User;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class UsersRepository {
    public List<User> get() {
        User user = new User();
        user.setId(1);
        user.setName("John Doe");
        user.setAge(21);
        user.setAvatar("https://api.adorable.io/avatars/285/john@example.com");

        Movie movie = new Movie();
        movie.setId(1);
        movie.setTitle("Inside Man");
        movie.setUrl("https://www.filmweb.pl/Plan.Doskonaly");

        user.setMovies(List.of(movie));

        return List.of(user);
    }
}
