package layers.service.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class User {

    @JsonProperty("userName")
    private String name;
    @JsonProperty("userPhone")
    private int phone;

    public User() {
    }

    public User(String name, int phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return phone == user.phone &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, phone);
    }
}