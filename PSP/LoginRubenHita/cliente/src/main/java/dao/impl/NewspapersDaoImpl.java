package dao.impl;

import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.NewspapersDao;
import dao.retrofit.llamadas.NewspapersAPI;
import domain.model.Newspaper;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class NewspapersDaoImpl extends DaoGenerics implements NewspapersDao {
    private final NewspapersAPI newspapersAPI;

    @Inject
    public NewspapersDaoImpl(NewspapersAPI newspapersAPI, Gson gson) {
        super(gson);
        this.newspapersAPI = newspapersAPI;
    }

    public Single<Either<String, List<Newspaper>>> getAllNewspapers(){
        return safeAPICall(newspapersAPI.getNewspapers());
    }

    public Single<Either<String, Newspaper>> getUnNewspaper(int id) {
        return safeAPICall(newspapersAPI.getUnNewspaper(id));
    }

    public Single<Either<String, Newspaper>> postNewspaper(Newspaper newspaper){
        return safeAPICall(newspapersAPI.postNewspaper(newspaper));
    }

    public Single<Either<String, Boolean>> deleteNewspaper(int id){
        return safeAPICallToDelete(newspapersAPI.deleteNewspaper(id));
    }
}
