package model;

import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Newspaper {
    private int id;
    private String name_newspaper;
    private Date release_date;

    public Newspaper() {
    }

    public Newspaper(int id, String nombre, Date localDate) {
        this.id = id;
        this.name_newspaper = nombre;
        this.release_date = localDate;
    }

    public Newspaper(String nombre, Date localDate) {
        this.name_newspaper = nombre;
        this.release_date = localDate;
    }

    public Newspaper(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName_newspaper() {
        return name_newspaper;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_newspaper(String name_newspaper) {
        this.name_newspaper = name_newspaper;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Newspaper that = (Newspaper) o;
        return Objects.equals(getName_newspaper(), that.getName_newspaper());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName_newspaper());
    }
}
