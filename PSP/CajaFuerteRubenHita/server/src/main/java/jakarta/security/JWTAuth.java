package jakarta.security;

import common.Constantes;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class JWTAuth implements HttpAuthenticationMechanism {
    @Inject
    private MyIdentityStore identity;

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , HttpMessageContext httpMessageContext) {

        CredentialValidationResult c = CredentialValidationResult.INVALID_RESULT;

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String[] valores = header.split(Constantes.EMPTY);
            if (valores[0].equalsIgnoreCase(Constantes.BASIC)) {

                c = identity.validate(new BasicAuthenticationCredential(valores[1]));

                if (c.getStatus() == CredentialValidationResult.Status.VALID) {
                    String token = Jwts.builder()
                            .setSubject(Constantes.TOKEN)
                            .setIssuer(Constantes.RUBEN)
                            .setExpiration(Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault())
                                    .toInstant()))
                            .claim(Constantes.USER, c.getCallerPrincipal().getName())
                            .claim(Constantes.ROLES, c.getCallerGroups())
                            .signWith(key).compact();

                    httpServletResponse.addHeader(HttpHeaders.AUTHORIZATION, Constantes.BEARER1 + token);
                } else {
                    return httpMessageContext.doNothing();
                }
            } else if (valores[0].equalsIgnoreCase(Constantes.BEARER)) {
                Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(valores[1]);
                List<String> roles = jws.getBody().get(Constantes.ROLES, List.class);
                Set<String> rolesSet = new HashSet<>(roles);
                String user = jws.getBody().get(Constantes.USER, String.class);
                c = new CredentialValidationResult(user, rolesSet);
            }
        } else {
            if (httpMessageContext.getAuthParameters().getCredential() != null){
                c = (CredentialValidationResult) httpMessageContext.getAuthParameters().getCredential();
            } else {
                c = CredentialValidationResult.NOT_VALIDATED_RESULT;
            }
        }

        return httpMessageContext.notifyContainerAboutLogin(c);
    }
}
