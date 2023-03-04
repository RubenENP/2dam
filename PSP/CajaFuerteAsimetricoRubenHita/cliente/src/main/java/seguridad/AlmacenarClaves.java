package seguridad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;

import java.util.Date;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

public class AlmacenarClaves {
    public AlmacenarClaves(char[] password) throws Exception {

        //Para generar la clave publica y privada
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey clavePrivada = keyPair.getPrivate();
        PublicKey clavePublica = keyPair.getPublic();

        //Para guardar la clave publica con un certificado
        Security.addProvider(new BouncyCastleProvider());
        X500Name owner = new X500Name("CN=Ruben");
        X500Name issuer = new X500Name("CN=Servidor");
        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                issuer,
                BigInteger.valueOf(1),
                new Date(),
                new Date(System.currentTimeMillis()+1000000),
                owner, SubjectPublicKeyInfo.getInstance(
                ASN1Sequence.getInstance(clavePublica.getEncoded()))
        );

        ContentSigner sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(clavePrivada);
        X509CertificateHolder holder = certGen.build(sigGen);
        X509Certificate cert = new JcaX509CertificateConverter().getCertificate(holder);

        //Para verificarlo
        cert.verify(clavePublica);

        //Para guardar la clave privada
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null, null);
        ks.setCertificateEntry("publicKey", cert);
        ks.setKeyEntry("privateKey", clavePrivada, password, new Certificate[]{cert});
        FileOutputStream fos = new FileOutputStream("keystore.pfx");
        ks.store(fos, password);
        fos.close();

        //leer la clave privada
        KeyStore ks2 = KeyStore.getInstance("PKCS12");
        ks2.load(new FileInputStream("keystore.pfx"), password);

        X509Certificate crtLoaded = (X509Certificate) ks2.getCertificate("publicKey");
        PrivateKey pkLoaded = (PrivateKey) ks2.getKey("privateKey", password);
    }
}
