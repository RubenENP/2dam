package dao;

import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import modelo.Carpeta;

import java.util.List;

public interface DaoCarpetas {
    Single<Either<String, List<Carpeta>>> getAll();

    Single<Either<String, Carpeta>> add(Carpeta carpeta);
    Single<Either<String, String>> desbloquearCarpeta(int idCarpeta, String pass);
}
