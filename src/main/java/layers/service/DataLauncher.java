package layers.service;

import layers.service.models.Hall;
import layers.service.models.Place;
import layers.service.models.User;

import java.util.List;

public interface DataLauncher {

    /**
     * Возвращает список всех доступных кинозалов.
     * @return
     */
    List<String> getAllCinema ();

    /**
     * Возвращает конкретный кинозал со списком всех мест.
     * @param cinemaName
     * @return
     */
    Hall getCertainCinema (String cinemaName);

    /**
     * Размещает в хранилище данные о купленном месте-
     * номер места + имя\номер покупателя.
     * @param user
     * @param place
     * @return
     */
    boolean takeThePlace(User user, Place place);

}