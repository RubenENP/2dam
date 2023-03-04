package dao;

import domain.model.reader.Reader;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

import java.util.List;

public interface ReadersDao {
    Single<Either<String, List<Reader>>> getAllReaders();
    Single<Either<String, Reader>> getUnReader(int id);
    Single<Either<String, Reader>> addReader(Reader reader);
    Single<Either<String, Reader>> updateReader(Reader reader);
}
