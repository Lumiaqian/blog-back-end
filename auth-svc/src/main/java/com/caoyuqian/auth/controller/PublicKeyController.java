package com.caoyuqian.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @author qian
 * @version V1.0
 * @Title: PublicKeyController
 * @Package: com.caoyuqian.auth.controller
 * @Description: RSA公钥开放接口
 * @date 2020/10/15 3:17 下午
 **/
@RestController
@AllArgsConstructor
@RequestMapping("oauth")
public class PublicKeyController {

    private KeyPair keyPair;

    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
