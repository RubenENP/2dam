package services;

import io.vavr.control.Either;
import modelo.Carpeta;

import java.util.List;

public interface ServicesCarpeta {
    Either<String, Carpeta> create(Carpeta carpeta);
    Either<String, List<Carpeta>> getAll(String username);

    Either<String, List<Carpeta>> getAll();

    Either<String, Carpeta> get(int id);

    Either<String, String> desbloquearCarpeta(int idCarpeta, String pass, String user);
}
