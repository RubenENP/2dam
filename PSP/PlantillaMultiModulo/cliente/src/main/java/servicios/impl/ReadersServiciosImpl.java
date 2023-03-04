package servicios.impl;

import dao.ReadersDao;
import domain.model.reader.Reader;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.ReadersServicios;

import java.time.LocalDate;
import java.util.List;

public class ReadersServiciosImpl implements ReadersServicios {
    private final ReadersDao readersDao;

    @Inject
    ReadersServiciosImpl(ReadersDao readersDao) {
        this.readersDao = readersDao;
    }

    public Single<Either<String, List<Reader>>> getAllReaders(){
        return readersDao.getAllReaders();
    }

    public Single<Either<String, Reader>> getReaderById(int id){
        return readersDao.getUnReader(id);
    }
    public Single<Either<String, Reader>> addReader(String name, LocalDate birthDate){
        Reader r = new Reader(name, birthDate);
        return readersDao.addReader(r);
    }

    public Single<Either<String, Reader>> updateReader(int id, String name, LocalDate birthDate){
        Reader r = new Reader(id, name, birthDate);
        return readersDao.updateReader(r);
    }
}
