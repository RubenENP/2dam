package modelo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Carpeta {
    private int id;
    private String name;
    private int permission;
    private String password;
    private String username;
}