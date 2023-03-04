package dao.impl;

import config.ConfigXML;
import dao.DaoReaders;
import io.vavr.control.Either;
import jakarta.xml.bind.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import model.reader.Reader;
import model.reader.Readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Log4j2
public class DaoReadersImpl implements DaoReaders {
    public Either<List<Reader>, String> getAll() {
        Either<List<Reader>, String> response;

        try {
            JAXBContext context = JAXBContext.newInstance(Readers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile= Paths.get(ConfigXML.getInstance().getProperty("xmlReadersPath"));

            Readers r = (Readers) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

            response = Either.left(r.getReader());
        } catch (IOException | JAXBException e){
            response = Either.right(e.getMessage());
        }

        return response;
    }

    public boolean delete(int id) {
        try {
            JAXBContext context = JAXBContext.newInstance(Readers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile= Paths.get(ConfigXML.getInstance().getProperty("xmlReadersPath"));

            Readers r = (Readers) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

            Reader reader = r.getReader().stream().filter(reader1 -> reader1.getId() == id).findFirst().orElse(null);

            r.getReader().remove(reader);

            marshaller.marshal(r,
                    Files.newOutputStream(xmlFile));
        } catch (IOException | JAXBException e){
            log.error(e.getMessage());
            return false;
        }

        return true;
    }


}
