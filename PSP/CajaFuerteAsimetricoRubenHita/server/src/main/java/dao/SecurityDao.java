package dao;

import io.vavr.control.Either;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.X509Certificate;

public interface SecurityDao {
    Either<String, X509Certificate> crearCertificado(String clavePublicaCliente, KeyStore ks, String password) throws KeyStoreException;
}
