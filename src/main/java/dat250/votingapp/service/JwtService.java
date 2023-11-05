package dat250.votingapp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "YOUR_SECRET_KEY";  // Make sure to keep this key private and secure
    private final long EXPIRATION_TIME = 3600 * 1000;  // 1 hour in milliseconds

    /**
     * Generate a JWT token for the given username.
     */
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * Validate the given JWT token and return its claims.
     * If the token is invalid, this method will throw an exception.
     */
    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Extract the username from the given JWT token.
     */
    public String getUsernameFromToken(String token) {
        return validateToken(token).getSubject();
    }

    /**
     * Check if the given JWT token has expired.
     */
    public boolean isTokenExpired(String token) {
        Date expiration = validateToken(token).getExpiration();
        return expiration.before(new Date());
    }

    /**
     * In case you want to invalidate a token (basic method - just checks if the token is expired or not).
     */
    public boolean invalidateToken(String token) {
        return isTokenExpired(token);
    }
}
