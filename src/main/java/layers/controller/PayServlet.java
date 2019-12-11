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

/**
 * Получает данные от payment_page.html и помещает их в БД.
 */
public class PayServlet extends HttpServlet {

    private final DataLauncher launcher = DBLauncher.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer row = Integer.parseInt(req.getParameter("rownumber"));
        Integer seat = Integer.parseInt(req.getParameter("seatnumber"));
        Integer phone = Integer.parseInt(req.getParameter("userphone"));
        String hallName = req.getParameter("hallname");
        String userName = req.getParameter("username");

        User user = new User(userName, phone);
        Place place = new Place(hallName, row, seat, true);
        launcher.takeThePlace(user, place);
    }
}