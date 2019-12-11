package layers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import layers.service.DBLauncher;
import layers.service.DataLauncher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Сервлет, возвращающий список всех существующих с БД кинозалов.
 */
public class HallsForChoiceServlet extends HttpServlet {

    private final DataLauncher launcher = DBLauncher.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();

        List<String> halls = launcher.getAllCinema();

        writer.append(mapper.writeValueAsString(halls));
        writer.flush();
    }
}