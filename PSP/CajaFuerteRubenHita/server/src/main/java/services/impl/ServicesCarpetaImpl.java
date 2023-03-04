package services.impl;

import dao.CarpetasDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import modelo.Carpeta;
import services.ServicesCarpeta;

import java.util.List;

public class ServicesCarpetaImpl implements ServicesCarpeta {
    private final CarpetasDao carpetasDao;

    @Inject
    public ServicesCarpetaImpl(CarpetasDao carpetasDao) {
        this.carpetasDao = carpetasDao;
    }

    @Override
    public Either<String, Carpeta> create(Carpeta carpeta) {
        return carpetasDao.create(carpeta);
    }

    @Override
    public Either<String, List<Carpeta>> getAll(String username){
        return carpetasDao.getAll(username);
    }

    @Override
    public Either<String, List<Carpeta>> getAll() {
        return carpetasDao.getAll();
    }

    @Override
    public Either<String, Carpeta> get(int id) {
        return carpetasDao.get(id);
    }

    @Override
    public Either<String, String> desbloquearCarpeta(int idCarpeta, String pass, String user) {
        return carpetasDao.desbloquearCarpeta(idCarpeta, pass, user);
    }


}
