package com.zgd.sso.encode;

import com.zgd.sso.encode.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密类
 */
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence password) {
        return String.valueOf(password);
    }

    /**
     * 调用这个方法来判断密码是否匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        log.info("校验密码 rawPassword:{}\tencodePassword:{}",rawPassword,encodePassword);
        // 判断密码是否存在
        if (rawPassword == null) {
            return false;
        }
        //通过md5加密后的密码
        return MD5Util.verify(String.valueOf(rawPassword),encodePassword);

    }
}