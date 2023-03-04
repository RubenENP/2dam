package servicios.impl;

import dao.NewspapersDao;
import domain.model.Newspaper;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servicios.NewspapersServicios;

import java.time.LocalDate;
import java.util.List;

public class NewspapersServiciosImpl implements NewspapersServicios {
    private final NewspapersDao newspapersDao;

    @Inject
    public NewspapersServiciosImpl(NewspapersDao newspapersDao) {
        this.newspapersDao = newspapersDao;
    }

    public Single<Either<String, List<Newspaper>>> getAllNewspapers(){
        return newspapersDao.getAllNewspapers();
    }

    public Single<Either<String, Newspaper>> getUnNewspaper(int id){
        return newspapersDao.getUnNewspaper(id);
    }

    public Single<Either<String, Newspaper>> postNewspaper(int id, String name, LocalDate date){
        Newspaper newspaper = new Newspaper(id, name, date);
        return newspapersDao.postNewspaper(newspaper);
    }

    public Single<Either<String, Boolean>> deleteNewspaper(int id){
        return newspapersDao.deleteNewspaper(id);
    }
}
