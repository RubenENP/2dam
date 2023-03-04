package services;

import io.vavr.control.Either;
import jakarta.xml.bind.JAXBException;
import model.readArticle.ReadArticle;

import java.io.IOException;
import java.util.List;

public interface ServicesReadArticle {
    Either<List<ReadArticle>, String> getAll();

    boolean addReadArticle(String id, String idReader, String idArticle, String rating) throws JAXBException, IOException;
}
