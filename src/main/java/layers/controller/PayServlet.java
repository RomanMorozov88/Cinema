package layers.controller;

import layers.service.DBLauncher;
import layers.service.DataLauncher;
import layers.service.models.Place;
import layers.service.models.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.net.URLEncoder.encode;

/**
 * Получает данные от payment_page.html и помещает их в БД.
 */
public class PayServlet extends HttpServlet {

    private final DataLauncher launcher = DBLauncher.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        Integer result = (Integer) req.getAttribute("answer");
        String msg = null;
        if (result == 1) {
            msg = "Спасибо!";
        } else if (result == 0) {
            msg = "Похоже, это место уже занято.";
        } else if (result == -1) {
            msg = "Что-то пошло не так.";
        }
        msg = encode(msg, "UTF-8");
        System.out.println("{\"answer\":\"" + msg + "\"}");
        writer.append("{\"answer\":\"" + msg + "\"}");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer row = Integer.parseInt(req.getParameter("rownumber"));
        Integer seat = Integer.parseInt(req.getParameter("seatnumber"));
        Integer phone = Integer.parseInt(req.getParameter("userphone"));
        String hallName = req.getParameter("hallname");
        String userName = req.getParameter("username");

        User user = new User(userName, phone);
        Place place = new Place(hallName, row, seat, true);
        int result = launcher.takeThePlace(user, place);
        req.setAttribute("answer", result);
        doGet(req, resp);
    }
}