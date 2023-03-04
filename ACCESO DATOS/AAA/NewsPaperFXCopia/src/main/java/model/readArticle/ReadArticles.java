package model.readArticle;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ReadArticles {
    @XmlElement
    private List<ReadArticle> readArticle;

    public void addReadArticle(ReadArticle readArticle) {
        this.readArticle.add(readArticle);
    }
}
