package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "login")

@NamedQueries({@NamedQuery(name = "HQL_GET_ALL_LOGIN", query = "from Login")})
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user")
    private String user;
    @Column(name = "password")
    private String password;
    @JoinColumn(name = "id_reader",referencedColumnName = "id")
    @OneToOne
    private ReaderHib idReader;

}
