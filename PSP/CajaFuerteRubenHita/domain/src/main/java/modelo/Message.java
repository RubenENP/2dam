package modelo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    private int id;
    private int idCarpeta;
    private String text;
}
