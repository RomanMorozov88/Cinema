package layers.service;

import layers.persistence.Config;
import layers.service.models.Hall;
import layers.service.models.Place;
import layers.service.models.User;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBLauncher implements DataLauncher {

    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBLauncher INSTANCE = new DBLauncher();

    private DBLauncher() {
        SOURCE.setDriverClassName(Config.getInstance().get("jdbc.driver"));
        SOURCE.setUrl(Config.getInstance().get("jdbc.url"));
        SOURCE.setUsername(Config.getInstance().get("jdbc.username"));
        SOURCE.setPassword(Config.getInstance().get("jdbc.password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DBLauncher getInstance() {
        try {
            INSTANCE.hallsTbleExistCheck();
            INSTANCE.sellTicketsTableExistCheck();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return INSTANCE;
    }

    @Override
    public List<String> getAllCinema() {
        List<String> result = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT * FROM dbHalls;")
        ) {
            while (resultSet.next()) {
                result.add(resultSet.getString("hall_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получаем конкретный кинотеатр.
     */
    @Override
    public Hall getCertainCinema(String cinemaName) {
        Hall result = null;
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        String.format("SELECT * FROM dbHalls WHERE hall_name = '%s';", cinemaName)
                )
        ) {
            while (resultSet.next()) {
                result = setCertainHallUp(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Помещаем выбранное место в таблицу.
     *
     * @param user
     * @param place
     */
    @Override
    public boolean takeThePlace(User user, Place place) {
        boolean result = false;
        try (
                Connection connection = SOURCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO dbSellTickets (hall_name, customer_name, customer_phone, seat_row, seat_number) VALUES (?, ?, ?, ?, ?);")
        ) {
            statement.setString(1, place.getHall());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getPhone());
            statement.setInt(4, place.getRow());
            statement.setInt(5, place.getSeat());
            result = !statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Получаем из БД проданные в конкретном кинотеатре места.
     *
     * @param cinemaName
     * @return
     */
    private List<Place> getSellTickets(String cinemaName) {
        List<Place> result = new ArrayList<>();
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        String.format("SELECT * FROM dbSellTickets WHERE hall_name = '%s';", cinemaName)
                )
        ) {
            while (resultSet.next()) {
                result.add(setSellPlace(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Получаем из таблицы имя нужного кинотеатра, его вместимость и статус мест.
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Hall setCertainHallUp(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("hall_name");
        int rows = resultSet.getInt("hall_rows");
        int seats = resultSet.getInt("hall_seats");
        int price = resultSet.getInt("hall_price");
        Map<Integer, List<Place>> places = new HashMap<>();
        List<Place> bufferList = null;
        List<Place> soldOut = this.getSellTickets(name);
        boolean bufferStatus = false;
        for (int i = 1; i <= rows; i++) {
            bufferList = new ArrayList<>();
            for (int j = 1; j <= seats; j++) {
                bufferStatus = this.checkPlace(i, j, soldOut);
                bufferList.add(new Place(name, i, j, bufferStatus));
            }
            places.put(i, bufferList);
        }
        Hall result = new Hall(name, seats, price, places);
        return result;
    }

    /**
     * Проверяет- продано ли место в ряду row с номером seat.
     *
     * @param row
     * @param seat
     * @param soldOut
     * @return
     */
    private boolean checkPlace(int row, int seat, List<Place> soldOut) {
        boolean result = false;
        for (Place p : soldOut) {
            if (p.getRow() == row && p.getSeat() == seat) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Получаем из таблицы имя нужного кинотеатра и его вместимость.
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private Place setSellPlace(ResultSet resultSet) throws SQLException {
        String hall = resultSet.getString("hall_name");
        int row = resultSet.getInt("seat_row");
        int seat = resultSet.getInt("seat_number");
        return new Place(hall, row, seat, true);
    }

    /**
     * Проверяем наличие таблицы, содержащей кинотеатры..
     * Если её нет- создаём.
     *
     * @throws SQLException
     */
    private void hallsTbleExistCheck() throws SQLException {
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS dbHalls"
                            + "(hall_name VARCHAR primary key,"
                            + "hall_rows INTEGER,"
                            + "hall_seats INTEGER,"
                            + "hall_price INTEGER);"
            );
        }
    }

    /**
     * Проверяем наличие таблицы, содержащей проданные билеты.
     * Если её нет- создаём.
     *
     * @throws SQLException
     */
    private void sellTicketsTableExistCheck() throws SQLException {
        try (
                Connection connection = SOURCE.getConnection();
                Statement statement = connection.createStatement()
        ) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS dbSellTickets"
                            + "(id SERIAL primary key,"
                            + "hall_name VARCHAR,"
                            + "customer_name VARCHAR,"
                            + "customer_phone INTEGER,"
                            + "seat_row INTEGER,"
                            + "seat_number INTEGER);"
            );
        }
    }
}