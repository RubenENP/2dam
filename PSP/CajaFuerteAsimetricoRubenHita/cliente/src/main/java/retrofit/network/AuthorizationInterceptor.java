package retrofit.network;

import common.Constantes;
import jakarta.inject.Inject;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;

public class AuthorizationInterceptor implements Interceptor {
    private CacheAuthorization ca;

    @Inject
    public AuthorizationInterceptor(CacheAuthorization cacheAuthorization) {
        this.ca = cacheAuthorization;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request newRequest;

        if (request.header("Logout") != null) {
            ca.setUser(null);
            ca.setPass(null);
            ca.setJwt(null);
        } else if (request.header(Constantes.AUTHORIZATION)!=null){
            String header = request.header(Constantes.AUTHORIZATION);
            String[] h = header.split(" ");
            String[] userypass = new String(Base64.getUrlDecoder().decode(h[1])).split(":");

            ca.setUser(userypass[0]);
            ca.setPass(userypass[1]);
        }

        if (ca.getJwt() != null) {
            newRequest = request.newBuilder().header(Constantes.AUTHORIZATION, ca.getJwt()).build();
        }  else {
            newRequest = request;
        }

        if (ca.getServerPublicKey() == null) {
            newRequest = request.newBuilder().header(Constantes.CERTIFICATE, "").build();
        }

        Response response = chain.proceed(newRequest);
        if (response.header(Constantes.AUTHORIZATION) != null) {
            String header = response.header(Constantes.AUTHORIZATION);
            ca.setJwt(header);
        }

        if (response.header(Constantes.CERTIFICATE) != null){
            String certificateHeader = response.header(Constantes.CERTIFICATE);
            byte[] certificadoDecodificado = Base64.getUrlDecoder().decode(certificateHeader);
            CertificateFactory cf;
            try {
                cf = CertificateFactory.getInstance("X.509");
                X509Certificate certificadoRecibido = (X509Certificate)cf.generateCertificate(
                        new ByteArrayInputStream(certificadoDecodificado));
                ca.setServerPublicKey(certificadoRecibido.getPublicKey());
            } catch (CertificateException e) {
                throw new RuntimeException(e);
            }
        }

        return response;
    }
}
