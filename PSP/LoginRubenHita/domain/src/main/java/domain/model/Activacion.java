package domain.model;

import lombok.*;

import java.sql.Time;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Activacion {
    private String cod;
    private Time time;
    private String user;
}
