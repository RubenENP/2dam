module seguridad{
    requires org.bouncycastle.provider;
    requires org.bouncycastle.pkix;

    exports claves;
    exports encriptacion;
}