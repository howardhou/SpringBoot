package com.example.jwtdemo;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Calendar;

public class JwtUtils {


    public static String getToken(User user){
        String token = "";

        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            //long expire = 7 * 24 * 60 * 60 * 1000; // 7天后过期
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, 10);  // 10分钟后过期

            token = JWT.create()
                    .withIssuer("jwtdemo")
                    .withClaim("userId", user.getUserId())
                    .withAudience(user.getUsername())
                    .withSubject(user.getUsername())
                    .withExpiresAt(calendar.getTime())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    public static boolean checkToken(String token){
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(
                Algorithm.HMAC256("secret")
        ).build();

        try {
            jwtVerifier.verify(token);

            return true;
        } catch (JWTVerificationException e) {
            throw new RuntimeException("401 verify error");
        }
        finally {
            return true;
        }
    }

}
