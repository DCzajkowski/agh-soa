package App.Servlets;

import App.Domain.CommentsService;
import App.Domain.ErrorBag;
import App.Domain.OldInput;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/comments")
public class GuestBookServlet extends HttpServlet {
    @Inject
    private CommentsService commentsService;

    @Inject
    private ErrorBag errors;

    @Inject
    private OldInput oldInput;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = Optional.ofNullable(request.getParameter("name")).map(String::trim).orElse("");
        String email = Optional.ofNullable(request.getParameter("email")).map(String::trim).orElse("");
        String comment = Optional.ofNullable(request.getParameter("comment")).map(String::trim).orElse("");

        if (name.isEmpty()) errors.put("name", "Name cannot be empty");
        if (email.isEmpty()) errors.put("email", "Email cannot be empty");
        if (comment.isEmpty()) errors.put("comment", "Comment cannot be empty");

        if (errors.hasAny()) {
            oldInput.put("name", name);
            oldInput.put("email", email);
            oldInput.put("comment", comment);

            request.getSession().setAttribute("errors", errors);
            response.sendRedirect("/comments");

            return;
        }

        commentsService.create(name, email, comment);

        oldInput.clear();

        response.sendRedirect("/comments");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("errors", errors);
        request.setAttribute("comments", commentsService.getComments());
        request.setAttribute("old", oldInput);

        request.getRequestDispatcher("comments.jsp").forward(request, response);
    }
}
