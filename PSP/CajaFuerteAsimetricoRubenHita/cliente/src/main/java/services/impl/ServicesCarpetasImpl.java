package services.impl;

import dao.DaoCarpetas;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.Carpeta;
import retrofit.network.CacheAuthorization;
import services.ServicesCarpetas;

import java.util.List;

public class ServicesCarpetasImpl implements ServicesCarpetas {
    private final DaoCarpetas daoCarpetas;
    private final CacheAuthorization ca;

    @Inject
    public ServicesCarpetasImpl(DaoCarpetas daoCarpetas, CacheAuthorization ca) {
        this.daoCarpetas = daoCarpetas;
        this.ca = ca;
    }

    @Override
    public Single<Either<String, List<Carpeta>>> getAll() {
        return daoCarpetas.getAll();
    }

    @Override
    public Single<Either<String, Carpeta>> add(String name, int permiso, String pass) {
        return daoCarpetas.add(new Carpeta(0, name, permiso, pass, ca.getUser()));
    }

    @Override
    public Single<Either<String, String>> desbloquearCarpeta(int idCarpeta, String pass) {
        return daoCarpetas.desbloquearCarpeta(idCarpeta, pass);
    }
}
