package App.Domain;

import javax.enterprise.context.ApplicationScoped;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@ApplicationScoped
public class AuthService implements Serializable {
    private final List<User> users;
    private final MessageDigest hashManager;

    public AuthService() {
        users = Arrays.asList(
            new User("admin", "5f4dcc3b5aa765d61d8327deb882cf99", "", "")
        );

        try {
            hashManager = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No md5 bro.", e);
        }
    }

    public boolean attemptLogin(String username, String password) {
        return users.stream()
            .filter(_user -> _user.getUsername().equals(username))
            .findFirst()
            .map(_user -> _user.getPassword().equals(DatatypeConverter.printHexBinary(hashManager.digest(password.getBytes())).toLowerCase()))
            .orElse(false);
    }
}
