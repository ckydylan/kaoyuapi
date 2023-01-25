package com.kaoyu.kaoyuapiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @Author: cky
 * @Date: 2023/1/14 22:41
 * @Description:
 */
public class SignUtil {
    /**
     * 生成签名
     * @param body
     * @param secretKey
     * @return
     */
    public static String getSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        return md5.digestHex(body + "." + secretKey);
    }
}
