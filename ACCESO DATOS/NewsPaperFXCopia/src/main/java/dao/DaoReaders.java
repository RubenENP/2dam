package dao;

import jakarta.xml.bind.JAXBException;
import model.reader.Reader;

import java.io.IOException;
import java.util.List;

public interface DaoReaders {
    List<Reader> getAll() throws IOException, JAXBException;
}
