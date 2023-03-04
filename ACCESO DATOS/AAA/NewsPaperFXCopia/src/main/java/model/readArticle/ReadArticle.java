package model.readArticle;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
public class ReadArticle {
    @XmlElement
    private int id;
    @XmlElement
    private int idReader;
    @XmlElement
    private int idArticle;
    @XmlElement
    private int rating;

    public ReadArticle() {
    }

    public ReadArticle(int id, int idReader, int idArticle, int rating) {
        this.id = id;
        this.idReader = idReader;
        this.idArticle = idArticle;
        this.rating = rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ReadArticle{id="+id+", idReader="+idReader+", idArticle"+idArticle+", rating="+rating;
    }
}
