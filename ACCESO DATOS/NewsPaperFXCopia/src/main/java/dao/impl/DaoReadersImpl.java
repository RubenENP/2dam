package dao.impl;

import config.ConfigXML;
import dao.DaoReaders;
import jakarta.xml.bind.*;
import model.reader.Reader;
import model.reader.Readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DaoReadersImpl implements DaoReaders {
    public List<Reader> getAll() throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(Readers.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        Unmarshaller unmarshaller = context.createUnmarshaller();
        Path xmlFile= Paths.get(ConfigXML.getInstance().getProperty("xmlReadersPath"));

        Readers r = (Readers) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

        return r.getReader();
    }
}
