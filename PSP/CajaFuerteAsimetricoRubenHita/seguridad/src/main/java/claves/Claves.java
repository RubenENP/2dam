package claves;

import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.jcajce.provider.symmetric.ARC4;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class Claves {
    public void generarYGuardarClaves(String pass) throws NoSuchAlgorithmException, CertificateException, KeyStoreException, IOException, OperatorCreationException, UnrecoverableEntryException {
        // Generar claves publicas y privadas
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048, new SecureRandom());
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey clavePrivada = keyPair.getPrivate();
        PublicKey clavePublica = keyPair.getPublic();

        // Generar certificado
        Security.addProvider(new BouncyCastleProvider());
        X500Name owner = new X500Name("CN=Ruben");
        X500Name issuer = new X500Name("CN=Servidor");
        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                issuer,
                BigInteger.valueOf(1),
                new Date(),
                new Date(System.currentTimeMillis() + 1000000),
                owner, SubjectPublicKeyInfo.getInstance(
                ASN1Sequence.getInstance(clavePublica.getEncoded()))
        );

        ContentSigner sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(clavePrivada);
        X509CertificateHolder holder = certGen.build(sigGen);
        X509Certificate cert = new JcaX509CertificateConverter().getCertificate(holder);
        char[]password = pass.toCharArray();

        // Guardar claves en un KeyStore
        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null, null);
        ks.setCertificateEntry("publicKey", cert);
        ks.setKeyEntry("privateKey", clavePrivada, password, new Certificate[]{cert});
        FileOutputStream fos = new FileOutputStream("keystore.jks");
        ks.store(fos, null);
        fos.close();

        // Cargar claves del KeyStore
        KeyStore ks1 = KeyStore.getInstance("PKCS12");
        ks1.load(new FileInputStream("keystore.jks"), null);
        System.out.println(ks1.getCertificate("publicKey"));
        PrivateKey privateKey = (PrivateKey) ks1.getEntry("privateKey", new KeyStore.PasswordProtection(password));

        // Para pasar las claves del cliente al servidor o servidor al cliente
        Base64.getUrlEncoder().encodeToString(clavePublica.getEncoded());
    }

    public X509Certificate crearCertificado(PublicKey clavePublica,PrivateKey clavePrivada, String ownerString, String issuerString) throws CertificateException, OperatorCreationException {
        Security.addProvider(new BouncyCastleProvider());
        X500Name owner = new X500Name(ownerString);
        X500Name issuer = new X500Name(issuerString);
        X509v3CertificateBuilder certGen = new X509v3CertificateBuilder(
                issuer,
                BigInteger.valueOf(1),
                new Date(),
                new Date(System.currentTimeMillis() + 1000000),
                owner, SubjectPublicKeyInfo.getInstance(
                ASN1Sequence.getInstance(clavePublica.getEncoded()))
        );

        ContentSigner sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption").build(clavePrivada);
        X509CertificateHolder holder = certGen.build(sigGen);

        return new JcaX509CertificateConverter().getCertificate(holder);
    }

    public PublicKey stringToPublicKey(String claveDesencriptada) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(claveDesencriptada.getBytes());
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }
}
