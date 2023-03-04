package model.reader;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class Reader {
    private int id;
    private String name;
    private LocalDate birthDate;

    public Reader() {
    }

    public Reader(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Reader(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Reader(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
