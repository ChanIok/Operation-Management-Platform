import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.qiniu.util.Base64;

import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;

import java.util.Calendar;


public class JWTTest {

    static String key = "uestc";
    static  String sign;

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR,1);

         JwtBuilder builder =  Jwts.builder()
                 .claim("username","小明")
                .claim("password",123)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS256,key);

        String sign = builder.compact();
        System.out.println(sign);

        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(sign).getBody();

        System.out.println((String) claims.get("username"));
        System.out.println((Integer) claims.get("password"));

    }

    @Test
    public void testVerify(){


        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(sign).getBody();

        System.out.println((String) claims.get("username"));
        System.out.println((String) claims.get("password"));


        // DecodedJWT verify = JWT.require(Algorithm.HMAC256(key)).build().verify(sign);



    }
}