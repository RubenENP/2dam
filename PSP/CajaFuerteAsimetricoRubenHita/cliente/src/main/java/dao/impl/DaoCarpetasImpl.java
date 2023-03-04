package dao.impl;

import com.google.gson.Gson;
import dao.DaoCarpetas;
import dao.DaoGenerics;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.Carpeta;
import retrofit.llamadas.CarpetaAPI;

import java.util.List;

public class DaoCarpetasImpl extends DaoGenerics implements DaoCarpetas {
    private final CarpetaAPI carpetaAPI;

    @Inject
    public DaoCarpetasImpl(Gson gson, CarpetaAPI carpetaAPI) {
        super(gson);
        this.carpetaAPI = carpetaAPI;
    }

    @Override
    public Single<Either<String, List<Carpeta>>> getAll(){
        return safeAPICall(carpetaAPI.getAll());
    }

    @Override
    public Single<Either<String, Carpeta>> add(Carpeta carpeta) {
        return safeAPICall(carpetaAPI.post(carpeta));
    }

    @Override
    public Single<Either<String, String>> desbloquearCarpeta(int idCarpeta, String pass) {
        return safeAPICall(carpetaAPI.desbloquear(idCarpeta, pass));
    }
}
