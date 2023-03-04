package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.ReadersDao;
import dao.retrofit.llamadas.ReadersAPI;
import domain.model.reader.Reader;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class ReadersDaoImpl extends DaoGenerics implements ReadersDao {
    private final ReadersAPI readersAPI;

    @Inject
    ReadersDaoImpl(ReadersAPI readersAPI, Gson gson) {
        super(gson);
        this.readersAPI = readersAPI;
    }


    public Single<Either<String, List<Reader>>> getAllReaders() {
        return safeAPICall(readersAPI.getAllReaders());
    }

    public Single<Either<String, Reader>> getUnReader(int id) {
        return safeAPICall(readersAPI.getUnReader(id));
    }

    public Single<Either<String, Reader>> addReader(Reader reader) {
        return safeAPICall(readersAPI.addReader(reader));
    }

    public Single<Either<String, Reader>> updateReader(Reader reader) {
        return safeAPICall(readersAPI.updateReader(reader));
    }
}
