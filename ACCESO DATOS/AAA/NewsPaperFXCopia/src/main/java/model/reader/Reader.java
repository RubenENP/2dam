package model.reader;

import dao.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.Getter;
import lombok.ToString;
import model.readArticle.ReadArticles;
import model.subscription.Subscriptions;

import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@ToString
public class Reader {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate birthDate;
    @XmlElement
    private Subscriptions subscriptions;
    @XmlElement
    private ReadArticles readArticles;

    public Reader() {
    }

    public Reader(int id, String name, LocalDate birthDate, Subscriptions subscriptions, ReadArticles readArticles) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.subscriptions = subscriptions;
        this.readArticles = readArticles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSubscriptions(Subscriptions subscriptions){this.subscriptions = subscriptions;}

    public void setReadArticles(ReadArticles readArticles){this.readArticles = readArticles;}


}
