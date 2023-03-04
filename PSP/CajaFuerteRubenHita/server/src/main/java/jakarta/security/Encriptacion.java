package jakarta.security;

public interface Encriptacion {
    String encriptar(String strToEncrypt, String secret);
    String desencriptar(String strToDecrypt, String secret);
}
