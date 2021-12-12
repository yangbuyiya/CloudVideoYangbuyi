/*
 * Yang Shuai  Copyright (c) 2021 https://yangbuyi.top.
 */

package top.yangbuyi.service_security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import top.yangbuyi.common_utils.constant.Constants;

import java.util.Date;

/**
 * 令牌管理器
 * token管理
 *
 * @author Yang Shuai
 * @date 2021/10/03
 */
@Component
public class TokenManager {

    /**
     * 根据用户名称创建一个token
     *
     * @param username
     * @return String
     */
    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + Constants.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, Constants.TOKEN_SECRET_KEY)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
    }

    /**
     * 根据token解析token串
     *
     * @param token
     * @return String
     */
    public String getUserFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Constants.TOKEN_SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
