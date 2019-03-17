package App.Filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(filterName = "AgeFilter", urlPatterns = {"/beer-picker", "/form.html", "/result.jsp"})
public class AgeFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("age") == null || ((int) session.getAttribute("age")) < 18) {
            // throw new ServletException("You must be at least 18 years old.");

            response.setStatus(403);

            PrintWriter writer = response.getWriter();
            writer.println("You must be at least 18 years old");
            writer.close();

            return;
        }

        chain.doFilter(request, response);
    }
}
