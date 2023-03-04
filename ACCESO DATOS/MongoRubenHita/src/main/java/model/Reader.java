package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Reader {
    private int _id;
    private String name;
    private String cancellationDate;
}
