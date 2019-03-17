package App.Servlets;

import App.Domain.AuthService;
import App.Domain.ErrorBag;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Inject
    ErrorBag errors;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = Optional.ofNullable(request.getParameter("username")).map(String::trim).orElse("");
        String password = Optional.ofNullable(request.getParameter("password")).map(String::trim).orElse("");

        if (username.isEmpty()) {
            errors.put("username", "Username cannot be empty");
        }

        if (password.isEmpty()) {
            errors.put("password", "Password cannot be empty");
        }

        AuthService authService = new AuthService();

        if (!authService.attemptLogin(username, password)) {
            errors.put("username", "Incorrect credentials");
            response.sendRedirect("/login");

            return;
        }

        request.getSession().setAttribute("isSignedIn", true);
        response.sendRedirect("/comments");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errors", errors);

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
