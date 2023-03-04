package retrofit.network;

import jakarta.inject.Singleton;
import lombok.Data;

import java.security.PublicKey;
import java.security.cert.X509Certificate;

@Data
@Singleton
public class CacheAuthorization {
    private String user;
    private String pass;
    private String jwt;
    private PublicKey serverPublicKey;
}
