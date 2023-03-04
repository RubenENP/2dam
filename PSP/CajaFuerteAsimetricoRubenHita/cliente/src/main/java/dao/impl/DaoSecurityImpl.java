package dao.impl;

import claves.Claves;
import com.google.gson.Gson;
import dao.DaoGenerics;
import dao.DaoSecurity;
import encriptacion.Encriptacion;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit.llamadas.SecurityAPI;
import retrofit.network.CacheAuthorization;

import java.security.*;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class DaoSecurityImpl extends DaoGenerics implements DaoSecurity{
    private final CacheAuthorization ca;
    private final SecurityAPI securityAPI;
    private final Encriptacion encriptacion;
    @Inject
    public DaoSecurityImpl(CacheAuthorization ca, Gson gson, SecurityAPI securityAPI, Encriptacion encriptacion) {
        super(gson);
        this.ca = ca;
        this.securityAPI = securityAPI;
        this.encriptacion = encriptacion;
    }

    public Single<Either<String, String>> getServerPublicKey() {
        return safeAPICall(securityAPI.getServerPublicKey());
    }

    public Single<Either<String, String>> crearClaves() throws NoSuchAlgorithmException {
        if (ca.getServerPublicKey() != null) {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048, new SecureRandom());
            KeyPair keyPair = keyGen.generateKeyPair();
            PrivateKey clavePrivada = keyPair.getPrivate();
            PublicKey clavePublica = keyPair.getPublic();

            String clavePublica64 = Base64.getUrlEncoder().encodeToString(clavePublica.getEncoded());
            String claveServidor64 = Base64.getUrlEncoder().encodeToString(ca.getServerPublicKey().getEncoded());

            String clavePublicaEncriptada = encriptacion.encriptar(clavePublica64, claveServidor64);

            safeAPICall(securityAPI.comprobarClave(clavePublicaEncriptada))
                    .observeOn(Schedulers.single())
                    .subscribe(either ->{
                        if(either.isRight()){
                            Claves claves = new Claves();
                            X509Certificate cert = claves.stringToCertificate(either.get());
                        }
                    }
                    );

            return safeAPICall(securityAPI.comprobarClave(clavePublicaEncriptada));
        } else {
            return null;
        }
    }
}
