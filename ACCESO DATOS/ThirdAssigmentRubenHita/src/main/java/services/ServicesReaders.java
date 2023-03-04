package services;

import io.vavr.control.Either;
import model.ReaderHib;

import java.util.List;

public interface ServicesReaders {
    Either<String, List<ReaderHib>> getAll();

    Either<String, ReaderHib> get(int id);
    Either<String, List<ReaderHib>> getAllByNewspaper(String newspaperName);
    Either<String, ReaderHib> delete(ReaderHib r);

    Either<String, ReaderHib> addReader(int parseInt, String name, String birthDate);

    Either<String, ReaderHib> update(String newName, int id, String birthDate);

    Either<String, List<ReaderHib>> getReaderOfAnArticleType(String description);
}
