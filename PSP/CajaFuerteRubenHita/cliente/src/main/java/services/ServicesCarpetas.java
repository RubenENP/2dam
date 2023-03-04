package services;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.Carpeta;

import java.util.List;

public interface ServicesCarpetas {
    Single<Either<String, List<Carpeta>>> getAll();
    Single<Either<String, Carpeta>> add(String name, int permiso, String pass);
    Single<Either<String, String>> desbloquearCarpeta(int idCarpeta, String pass);
}
