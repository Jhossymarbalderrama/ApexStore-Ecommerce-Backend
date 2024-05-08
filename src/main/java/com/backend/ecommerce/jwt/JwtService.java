
package com.backend.ecommerce.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 *
 * @author balde
 */
@Service
public class JwtService {
    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    /**
     * *
     * Devuelve el Token
     *
     * @param user
     * @return
     */
    public String getToken(UserDetails user) {
        return getToken(new HashMap<>(), user);
    }

    /**
     * *
     * Armo el Token y lo devuelvo
     *
     * @param extraClaims
     * @param user
     * @return
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))//Expiracion de token  //30 min de expiracion
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * *
     * Codifico la Password Hardcodeado en este caso
     *
     * @return
     */
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * *
     * Obtengo el User name desde el Token
     *
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    /**
     * *
     * Valida si el username pasado en el token es igual al user en UserDetails
     * y si el token no expiro devuelve True caso contrario False
     *
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * *
     * Obtengo todas las Claims del Token
     *
     * @param token
     * @return
     */
    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * *
     * Obtengo un Claim en Particular
     *
     * @param <T>
     * @param token
     * @param claimsResolver
     * @return
     */
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * *
     * Metodo que devuelve la fecha de expiracion del token
     *
     * @param token
     * @return
     */
    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    /**
     * *
     * Retorna la Fecha actual si el token pasado por parametro a expirado
     *
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
