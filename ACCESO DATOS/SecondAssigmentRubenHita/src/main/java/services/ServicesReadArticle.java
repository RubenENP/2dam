package services;

import io.vavr.control.Either;
import jakarta.xml.bind.JAXBException;
import model.readArticle.ReadArticle;

import java.io.IOException;
import java.util.List;

public interface ServicesReadArticle {
    Either<List<ReadArticle>, String> getAll();

    int addReadArticle(int idReader, String articleName, int rating);

    ReadArticle update(ReadArticle readArticle,int rate);
}
