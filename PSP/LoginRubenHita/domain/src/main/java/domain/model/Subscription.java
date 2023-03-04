package domain.model;

import lombok.Getter;

import java.sql.Date;

@Getter
public class Subscription {
    private int idNewspaper;
    private int idReader;
    private Date startDate;
    private Date cancellationDate;

    public Subscription(int idNewspaper, int idReader, Date startDate, Date cancellationDate) {
        this.idNewspaper = idNewspaper;
        this.idReader = idReader;
        this.startDate = startDate;
        this.cancellationDate = cancellationDate;
    }
}
