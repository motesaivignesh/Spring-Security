package com.authentication.SpringSec.Service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.server.ServerEndpoint;

@Service
public class Jwtservice {
    private final String secretkey;
    public Jwtservice(){
        try{
            KeyGenerator keygen  = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keygen.generateKey();
            secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException(e); 
        }
    }
    public String generatetoken(String username) {
        HashMap<String,Object> claims = new HashMap<>();
        return Jwts.builder()
        .claims()
        .add(claims)
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+1000*60*30))
        .and()
        .signWith(getKey())
        .compact();

    }

    private Key getKey() {
        byte[] keybytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keybytes);
    }
    public  String extractUsername(String token) {
    return extractAllClaims(token)
            .getSubject();
}
    public boolean validateToken(
        String token,
        UserDetails userDetails) {

    String username =
            extractUsername(token);

    return username.equals(
            userDetails.getUsername())
            &&
            !isTokenExpired(token);
}
    private  Claims extractAllClaims(String token) {
    return Jwts.parser()
            .verifyWith((SecretKey) getKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
public Date extractExpiration(String token) {

    return extractAllClaims(token)
            .getExpiration();
}
private boolean isTokenExpired(String token) {

    return extractExpiration(token)
            .before(new Date());
}

}
