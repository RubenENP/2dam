package domain.model.reader;

import java.util.ArrayList;
import java.util.List;

public class Readers {
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
