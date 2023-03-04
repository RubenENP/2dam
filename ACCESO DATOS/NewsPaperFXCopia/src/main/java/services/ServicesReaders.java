package services;

import jakarta.xml.bind.JAXBException;
import model.reader.Reader;

import java.io.IOException;
import java.util.List;

public interface ServicesReaders {
    List<Reader> getAll() throws JAXBException, IOException;

    List<Reader> getReadersByArticleType(String name) throws JAXBException, IOException;

    List<Reader> getReadersByNewspaper(String newspaperName) throws IOException, JAXBException;
}
