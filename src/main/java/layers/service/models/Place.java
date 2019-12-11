package layers.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Модель места. Содержит номер ряда, номер места и статус
 * true- если оно уже куплено, false- если свободно.
 */
public class Place {

    @JsonProperty("seatHall")
    private String hall;
    @JsonProperty("seatRow")
    private int row;
    @JsonProperty("seatNumber")
    private int seat;
    @JsonProperty("seatStatus")
    private boolean status;

    public Place() {
    }

    public Place(String hall, int row, int seat, boolean status) {
        this.hall = hall;
        this.row = row;
        this.seat = seat;
        this.status = status;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return row == place.row &&
                seat == place.seat &&
                status == place.status &&
                Objects.equals(hall, place.hall);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hall, row, seat, status);
    }
}