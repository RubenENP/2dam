package model.reader;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Readers {
    @XmlElement
    private List<Reader> reader;

    public Readers() {
        reader = new ArrayList<>();
    }

    public List<Reader> getReader() {
        return reader;
    }

    public void setReader(List<Reader> readerList) {
        this.reader = readerList;
    }

    public void addReader(Reader r) {
        reader.add(r);
    }

    @Override
    public String toString() {
        return "Readers{"+"reader="+reader+'}';
    }
}
