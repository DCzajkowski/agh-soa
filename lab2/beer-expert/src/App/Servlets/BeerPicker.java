package App.Servlets;

import App.Domain.BeerColor;
import App.Domain.BeerExpert;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/beer-picker")
public class BeerPicker extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String color = request.getParameter("color");

        if (color == null) {
            return;
        }

        BeerExpert beerExpert = new BeerExpert();
        String beer = beerExpert.getBeerProposition(BeerColor.valueOf(color.toUpperCase()));

        request.setAttribute("beer", beer);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
