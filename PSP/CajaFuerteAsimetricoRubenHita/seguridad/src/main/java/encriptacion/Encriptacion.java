package encriptacion;

public interface Encriptacion {
    String encriptar(String strToEncrypt, String secret);
    String desencriptar(String strToDecrypt, String secret);
}
