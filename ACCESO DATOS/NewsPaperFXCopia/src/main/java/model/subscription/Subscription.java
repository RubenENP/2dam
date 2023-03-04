package model.subscription;

import dao.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@XmlRootElement (name = "subscription")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
public class Subscription {
    @XmlElement
    private int id;
    @XmlElement
    private int idReader;
    @XmlElement
    private int idNewspaper;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate signingDate;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate cancellationDate;

    public Subscription() {
    }

    public Subscription(int id, int idReader, int idNewspaper, LocalDate signingDate, LocalDate cancellationDate) {
        this.id = id;
        this.idReader = idReader;
        this.idNewspaper = idNewspaper;
        this.signingDate = signingDate;
        this.cancellationDate = cancellationDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public void setIdNewspaper(int idNewspaper) {
        this.idNewspaper = idNewspaper;
    }
}
