package services.impl;

import dao.ReaderDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Newspaper;
import model.Reader;
import services.ServicesReader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ServicesReaderImpl implements ServicesReader {
    private final ReaderDao readerDao;

    @Inject
    public ServicesReaderImpl(ReaderDao readerDao) {
        this.readerDao = readerDao;
    }

    @Override
    public Either<String, List<Reader>> getAll() {
        return readerDao.getAll();
    }

    @Override
    public Either<String, List<Reader>> getAll(Newspaper newspaper) {
        Either<String, List<Reader>> readers = readerDao.getAll(newspaper);

        if (readers.isLeft()){
            return readers;
        } else {
            List<Reader> list = new ArrayList<>();

            readers.get().forEach(reader -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate cancelationDate = LocalDate.parse(reader.getCancellationDate(), formatter);
                if (cancelationDate.isAfter(LocalDate.now())){
                    list.add(reader);
                }
            });

            return Either.right(list);
        }
    }

    @Override
    public Either<Integer, Reader> save(String nameReader, String cancellationDate, Newspaper newspaper) {
        Reader r = new Reader(0, nameReader, cancellationDate);
        int i = readerDao.save(r, newspaper);
        Either<Integer, Reader> response;
        if (i != 0){
            response = Either.left(i);
        } else {
            response = Either.right(r);
        }
        return response;
    }

    @Override
    public Either<Integer, Reader> update(Reader reader, Newspaper newspaper, Integer id) {
        int i = readerDao.update(reader, newspaper, id);
        if (i != 0){
            return Either.left(i);
        } else {
            return Either.right(reader);
        }
    }

    @Override
    public Either<String, Reader> delete(Reader reader) {
        Either<String, Reader> response;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = (LocalDate) formatter.parse(reader.getCancellationDate());
        if (localDate.isBefore(LocalDate.now())){
            response = Either.right(reader);
        } else {
            response = Either.left("");
        }
        return response;
    }
}
