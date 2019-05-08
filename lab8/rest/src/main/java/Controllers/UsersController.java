package Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

@Path("/users")
public class UsersController {
    @GET
    @Produces("text/plain")
    public String index() {
        return "users";
    }
}
