package App.Domain;

import App.Helpers.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class AuthService implements Serializable {
    private final List<User> users;

    public AuthService() {
        users = Arrays.asList(
            new User("admin", "$2a$10$awMVKp0eI5.NC8efP4ppUuGb1ffG4k5VJJoyCY27yH/Pe0bu7zVtK", "", "")
        );
    }

    public boolean attemptLogin(String username, String password) {
        return users.stream()
            .filter(_user -> _user.getUsername().equals(username))
            .findFirst()
            .map(_user -> BCrypt.checkpw(password, _user.getPassword()))
            .orElse(false);
    }
}
