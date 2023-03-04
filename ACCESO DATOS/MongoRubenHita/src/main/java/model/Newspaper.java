package model;

import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Newspaper {
    private ObjectId _id;
    private String name;
    private String relDate;
    private List<Article> articles;

    private List<Reader> readers;

    public Newspaper(ObjectId _id, String name, String relDate) {
        this._id = _id;
        this.name = name;
        this.relDate = relDate;
    }

    @Override
    public String toString() {
        return name;
    }
}
