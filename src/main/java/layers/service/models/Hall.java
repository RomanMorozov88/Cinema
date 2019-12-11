package layers.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Модель кинозала. Содержит в себе название, количество мест в ряду
 * (необходимо для корректного отображения таблицы в ./index.html)
 * и карту {номер ряда : список мест в этом ряду}.
 */
public class Hall {

    @JsonProperty("hallName")
    private String hallName;
    @JsonProperty("seatsCount")
    private int seatsCount;
    @JsonProperty("placePrice")
    private int price;
    @JsonProperty("placeList")
    private Map<Integer, List<Place>> places;

    public Hall() {
    }

    public Hall(String name, int seatsCount, int price, Map<Integer, List<Place>> places) {
        this.hallName = name;
        this.seatsCount = seatsCount;
        this.price = price;
        this.places = places;

    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getSeatsCount() {
        return seatsCount;
    }

    public void setSeatsCount(int seatsCount) {
        this.seatsCount = seatsCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Map<Integer, List<Place>> getPlaces() {
        return places;
    }

    public void setPlaces(Map<Integer, List<Place>> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return seatsCount == hall.seatsCount &&
                price == hall.price &&
                Objects.equals(hallName, hall.hallName) &&
                Objects.equals(places, hall.places);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hallName, seatsCount, price, places);
    }
}