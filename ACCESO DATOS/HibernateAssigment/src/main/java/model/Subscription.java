package model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
@Table(name = "subscribe")

@NamedQueries({@NamedQuery(name = "HQL_GET_ALL_SUBSCRIBE", query = "from Subscription"),
        @NamedQuery(name = "HQL_GET_SUBSCRIBE_FROM_A_READER", query = "from Subscription s WHERE s.reader.id=:idReader")})
public class Subscription {
    @Id
    @JoinColumn(name = "id_newspaper",referencedColumnName = "id")
    @ManyToOne
    private NewspaperHib newspaper;
    @Id
    @JoinColumn(name = "id_reader", referencedColumnName = "id")
    @ManyToOne
    private ReaderHib reader;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "cancellation_date")
    private Date cancellationDate;
}
