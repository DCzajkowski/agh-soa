import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@WebServlet("/average")
public class Average extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Napisz servlet, który będzie przyjmował dowolną ilość parametrów metodą POST i sprawdzał, czy parametry te są
        // liczbami. Jeśli są, niech je wyświetli w kolejności od najmniejszej do największej. Jeśli parametry nie są
        // liczbami, niech servlet zwróci informacje o błędnych danych.

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        try {
            String numbersSorted = request.getParameterMap()
                .values()
                .stream()
                .map(values -> Double.parseDouble(values[0]))
                .sorted()
                .map(value -> (value.longValue() == value) ? ("" + value.longValue()) : ("" + value))
                .collect(Collectors.joining(", "));

            out.println(numbersSorted);
        } catch (NumberFormatException e) {
            throw new HTTPException(422);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter out = response.getWriter();

        Supplier<IntStream> numbers = () -> Arrays.stream(request.getParameterValues("numbers")).mapToInt(Integer::new);

        if (numbers.get().count() == 0) {
            throw new HTTPException(422);
        }

        double average = (double) numbers.get().sum() / numbers.get().count();

        out.println("<html>");
        out.println("<head><title>Test response</title></head>");
        out.println("<body>");
        out.println("<p>" + average + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
