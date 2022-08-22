package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author pssBerg
 * @version 1.0
 * @date 2022 08 12, 9:45
 * 工具包
 */

public class CommunityUtil {

    // 生成随机字符串，激活码、随机名字之类的
    public static String generateUUID() {
        // 只需要字母
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // MD5加密，不能明文存储密码，只能加密，不能解密
    // hello -> abc123def456
    // User里有个salt盐
    // hello + 3e4a8（随机字符串） -> abc123def456abc
    public static String md5(String key) {
        // key是密码
        // spring自带
        // apache.commons.lang3
        // 判断是否为空
        if (StringUtils.isBlank(key)) {
            return null;
        }
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }
}
