package com.spark.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.MalformedJwtException;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 使用 final 修饰符确保线程安全
    private static final String signKey = "c3Bhcms="; // 建议从配置文件加载
    private static final Long expire = 43200000L; // 建议从配置文件加载

    /**
     * 创建JWT令牌
     * @param claims 存储在JWT中的声明信息
     * @return 生成的JWT令牌
     */
    public static String createJwtToken(Map<String, Object> claims) {
        if (claims == null) {
            throw new IllegalArgumentException("Claims cannot be null");
        }

        long currentTimeMillis = System.currentTimeMillis(); // 避免重复计算
        return Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .setExpiration(new Date(currentTimeMillis + expire))
                .compact();
    }

    /**
     * 解码JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     * @throws IllegalArgumentException 如果JWT解析失败
     */
    public static Claims decodeJwtToken(String jwt) {
        if (jwt == null || jwt.isEmpty()) {
            throw new IllegalArgumentException("JWT cannot be null or empty");
        }

        try {
            return Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("JWT has expired", e);
        } catch (SignatureException e) {
            throw new IllegalArgumentException("Invalid JWT signature", e);
        } catch (MalformedJwtException e) {
            throw new IllegalArgumentException("Malformed JWT", e);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JWT", e);
        }
    }
}
