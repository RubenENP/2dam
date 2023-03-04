package dao.impl;

import config.ConfigXML;
import dao.DaoReadArticle;
import dao.DaoReaders;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.fxml.FXML;
import lombok.extern.log4j.Log4j2;
import model.Article;
import model.readArticle.ReadArticle;
import model.reader.Reader;
import model.reader.Readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Log4j2
public class DaoReadArticleImpl implements DaoReadArticle {
    DaoReaders daoReaders;

    @Inject
    DaoReadArticleImpl (DaoReaders daoReaders){
        this.daoReaders = daoReaders;
    }

    public Either<List<ReadArticle>, String> getAll() {
        Either<List<ReadArticle>, String> response;

        List<ReadArticle> responseList = new ArrayList<>();

        List<Reader> readerList;

        if (daoReaders.getAll().isLeft()){
            readerList = daoReaders.getAll().getLeft();
            readerList.forEach(reader -> responseList.addAll(reader.getReadArticles().getReadArticle()));
            response = Either.left(responseList);
        } else {
            response = Either.right(daoReaders.getAll().get());
        }

        return response;
    }

    public boolean addReadArticle(int id, int idReader, int idArticle, int rating) {
        boolean response = false;

        try {
            JAXBContext context = JAXBContext.newInstance(Readers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile= Paths.get(ConfigXML.getInstance().getProperty("xmlReadersPath"));

            Readers r = (Readers) unmarshaller.unmarshal(Files.newInputStream(xmlFile));

            Reader articleList = r.getReader().stream().filter(reader -> reader.getId() == idReader)
                    .findFirst().orElse(null);

            if(articleList != null){
                    articleList.getReadArticles().getReadArticle().add(new ReadArticle(id, idReader, idArticle, rating));
                    response = true;
            }

            marshaller.marshal(r,
                    Files.newOutputStream(xmlFile));
        } catch (IOException | JAXBException e) {
            log.error(e.getMessage());
            response = false;
        }

        return response;
    }
}
