package domain.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Usuario {
    public Usuario(String user, String password) {
        this.user = user;
        this.password = password;
    }

    private String user;
    private String password;
    private String email;
    private int activado;
    private String role;
}
