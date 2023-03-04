package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Article {
    private int id;
    private String title;
    private String desc;
    private String type;

    @Override
    public String toString() {
        return title;
    }
}
