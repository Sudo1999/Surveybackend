package survey.backend.auth;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import survey.backend.exception.*;
import java.util.Date;

@Component
public class JwtUtil {
    // JwtUtil contient deux fonctions appelées par JwtAuthenticationFilter, validateToken() et getUserName(),
    // et une fonction generateToken() appelée par le contrôleur JwtRestController
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public String generateToken(Authentication authentication) {    // generateToken est appelé dans JwtRestController
        org.springframework.security.core.userdetails
                .User user = (User) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(user.getUsername());   // getUsername, pas getUserName !!!
        final long nowMillis = System.currentTimeMillis();
        final long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void validateToken(final String token) {     // validateToken est appelé dans JwtAuthenticationFilter
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty");
        }
    }

    public String getUserName(final String token) {     // getUserName est appelé dans JwtAuthenticationFilter
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body.getSubject();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }
}
