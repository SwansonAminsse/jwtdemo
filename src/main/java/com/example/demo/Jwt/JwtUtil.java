package com.example.demo.Jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

public class JwtUtil {


    public static final String ACCOUNT = "user_name";
    public final static String CURRENT_TIME_MILLIS = "currentTimeMillis";
    public static final long EXPIRE_TIME = 2*60*60*1000L;

    public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;
    public static final String SECRET_KEY = "key";

    private static final ThreadLocal<String> tokenThreadLocal = new InheritableThreadLocal<>();
    public static String sign(String account, String currentTimeMillis) {

        String secret = account + SECRET_KEY;

        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
               .withClaim(ACCOUNT, account)
               .withClaim(CURRENT_TIME_MILLIS, currentTimeMillis)
               .withExpiresAt(date)
               .sign(algorithm);
        tokenThreadLocal.set(token);
        return token;
    }


    public static boolean verify(String token) {
        String secret = getClaim(token, ACCOUNT) + SECRET_KEY;
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm)
                .acceptExpiresAt(EXPIRE_TIME )
                .build();
        verifier.verify(token);
        return true;
    }


    public static String getClaim(String token, String claim) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(claim).asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    public static String getToken() {

        return tokenThreadLocal.get();
    }

    public static void clearToken() {

        tokenThreadLocal.remove();
    }

}
