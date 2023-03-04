package dao.impl;

import claves.Claves;
import encriptacion.Encriptacion;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class SecurityDaoImpl {
    private final Encriptacion encriptacion;

    @Inject
    public SecurityDaoImpl(Encriptacion encriptacion) {
        this.encriptacion = encriptacion;
    }

    public Either<String, X509Certificate> crearCertificado(String clavePublicaCliente, KeyStore ks, String password) throws KeyStoreException {
        X509Certificate cert = (X509Certificate) ks.getCertificate("publicKey");
        byte[] certBytes = cert.getPublicKey().getEncoded();
        String certString = Base64.getUrlEncoder().encodeToString(certBytes);

        String claveDesencriptada = encriptacion.desencriptar(clavePublicaCliente, certString);

        Claves claves = new Claves();
        try {
            KeyStore.PasswordProtection pt = new KeyStore.PasswordProtection(password.toCharArray());
            KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("privateKey", pt);
            PrivateKey keyLoad = privateKeyEntry.getPrivateKey();
            PublicKey publicKey = claves.stringToPublicKey(claveDesencriptada);
            return Either.right(claves.crearCertificado(publicKey, keyLoad, "CN=Ruben", "CN=Servidor"));
        } catch (Exception e) {
            return Either.left("Error al crear certificado");
        }
    }
}
