package App.Filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebFilter(filterName = "AuthFilter", urlPatterns = {"/comments"})
public class AuthFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        boolean isSignedIn = (boolean) Optional.ofNullable(request.getSession().getAttribute("isSignedIn")).orElse(false);

        if (!isSignedIn) {
            response.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }
}
