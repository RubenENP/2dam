package services.impl;

import dao.DaoSecurity;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import services.ServicesSecurity;

public class ServicesSecurityImpl implements ServicesSecurity {
    private final DaoSecurity daoSecurity;

    @Inject
    public ServicesSecurityImpl(DaoSecurity daoSecurity) {
        this.daoSecurity = daoSecurity;
    }

    public Single<Either<String, String>> getServerPublicKey() {
        return daoSecurity.getServerPublicKey();
    }
}
