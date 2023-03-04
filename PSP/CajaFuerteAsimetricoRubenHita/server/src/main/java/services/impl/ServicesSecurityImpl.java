package services.impl;

import dao.SecurityDao;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import services.ServicesSecurity;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;

public class ServicesSecurityImpl implements ServicesSecurity {
    private final SecurityDao securityDao;

    @Inject
    public ServicesSecurityImpl(SecurityDao securityDao) {
        this.securityDao = securityDao;
    }

    @Override
    public Either<String, X509Certificate> crearCertificado(String clavePublicaCliente, KeyStore ks, String password) throws KeyStoreException {
        return securityDao.crearCertificado(clavePublicaCliente, ks, password);
    }
}
