package layers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import layers.service.DBLauncher;
import layers.service.DataLauncher;
import layers.service.models.Hall;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Сервлет, возвращающий конкретный кинозал со списком\статусом всех мест.
 */
public class HallServlet extends HttpServlet {

    private final DataLauncher launcher = DBLauncher.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();

        String name = req.getParameter("currentCine");
        Hall testHall = launcher.getCertainCinema(name);

        writer.append(mapper.writeValueAsString(testHall));
        writer.flush();
    }
}